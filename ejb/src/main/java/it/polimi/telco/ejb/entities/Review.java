package it.polimi.telco.ejb.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User userID;

    @Column(name = "Email", nullable = false, length = 45)
    private String email;

    @Column(name = "Amount", precision = 5, scale = 2)
    private BigDecimal amount;

    @Column(name = "LastRejection", nullable = false)
    private Timestamp lastRejection;

    public Timestamp getLastRejection() {
        return lastRejection;
    }

    public void setLastRejection(Timestamp lastRejection) {
        this.lastRejection = lastRejection;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}