package it.polimi.telco.ejb.entities;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "`Order`", indexes = {
        @Index(name = "FK_Order_SUbscription_idx", columnList = "SubscriptionID"),
        @Index(name = "FK_Order_User_idx", columnList = "UserID")
})
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User userID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "SubscriptionID", nullable = false)
    private Subscription subscriptionID;

    @Column(name = "CreationDate", nullable = false)
    private Instant creationDate;

    @Column(name = "Validity")
    private Boolean validity;

    public Boolean getValidity() {
        return validity;
    }

    public void setValidity(Boolean validity) {
        this.validity = validity;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Subscription getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(Subscription subscriptionID) {
        this.subscriptionID = subscriptionID;
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