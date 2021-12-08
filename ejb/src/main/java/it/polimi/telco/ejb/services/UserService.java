package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.User;
import it.polimi.telco.ejb.exceptions.CredentialsException;
import it.polimi.telco.ejb.exceptions.AlreadyRegisteredException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless(name = "UserServiceEJB")
public class UserService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public UserService() {
    }

    public User checkCredentials(String username, String password) throws CredentialsException {
        List<User> users = null;
        try {
            users = em.createNamedQuery("User.checkCredentials", User.class)
                    .setParameter("usr", username)
                    .setParameter("pwd", password)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new CredentialsException("Could not verify credentials");
        }

        if (users.size() != 1) {
            return null;
        }

        return users.get(0);
    }

    public User registerUser(String email, String username, String password) throws CredentialsException, AlreadyRegisteredException {
        List<User> user = null;
        try {
            user = em.createNamedQuery("User.getByUsername", User.class)
                    .setParameter("usr", username)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new CredentialsException("Could not verify credentials");
        }

        if (!user.isEmpty()) {
            throw new AlreadyRegisteredException("Username already registered");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(password);

        em.persist(newUser);
        return newUser;
    }


    public User getUserById(String username){

        User user = em.find(User.class, username);

        if (user == null) {
            return null;
        }

        em.refresh(user);

        return user;
    }

}
