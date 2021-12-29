package it.polimi.telco.ejb.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Employee.checkCredentials",
                query = "SELECT e FROM Employee e WHERE e.username = :usr AND e.password = :pwd")
})

@Entity
public class Employee {
    @Id
    @Column(name = "EmployeeID", nullable = false)
    private Integer id;

    @Column(name = "Username", nullable = false, length = 45)
    private String username;

    @Column(name = "Password", nullable = false, length = 45)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}