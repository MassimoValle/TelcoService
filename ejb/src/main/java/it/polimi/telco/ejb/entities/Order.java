package it.polimi.telco.ejb.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Table(name = "`Order`", indexes = {
        @Index(name = "FK_Order_SUbscription_idx", columnList = "SubscriptionID"),
        @Index(name = "FK_Order_User_idx", columnList = "UserID")
})

@NamedQueries({
        @NamedQuery(name = "Order.getRejectedOrders",
                query = "SELECT o FROM Order o WHERE o.userID = :usr AND o.status = 'insolvent'"),
        @NamedQuery(name = "Order.getOrder",
                query = "SELECT o FROM Order o WHERE o.userID = :usr AND o.subscriptionID = :sbcr")
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
    private Timestamp creationDate;

    @Column(name = "Attempt", nullable = false)
    private Integer attempt;

    @Lob
    @Column(name = "Status", nullable = false)
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
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