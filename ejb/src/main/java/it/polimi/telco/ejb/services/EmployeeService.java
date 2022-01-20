package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.Employee;
import it.polimi.telco.ejb.exceptions.CredentialsException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless(name = "EmployeeServiceEJB")
public class EmployeeService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public EmployeeService(){}

    public Employee checkCredentials(String username, String password) throws CredentialsException {
        List<Employee> employees;
        try {
            employees = em.createNamedQuery("Employee.checkCredentials", Employee.class)
                    .setParameter("usr", username)
                    .setParameter("pwd", password)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new CredentialsException("Can't verify credentials");
        }

        if (employees.size() != 1) {
            return null;
        }

        return employees.get(0);
    }
}
