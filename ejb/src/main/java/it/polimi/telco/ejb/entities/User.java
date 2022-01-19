package it.polimi.telco.ejb.entities;

import javax.persistence.*;


@NamedQueries({
        @NamedQuery(name = "User.checkCredentials",
                query = "SELECT u FROM User u WHERE u.username = :usr AND u.password = :pwd"),
        @NamedQuery(name = "User.getByUsername",
                query = "SELECT u FROM User u WHERE u.username = :usr")
})

@Entity
public class User {
    @Id
    @Column(name = "Username", nullable = false, length = 45)
    private String username;

    @Column(name = "Email", nullable = false, length = 45)
    private String email;

    @Column(name = "Password", nullable = false, length = 45)
    private String password;

    @Column(name = "Status", nullable = false, length = 45)
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String id) {
        this.username = id;
    }
}