package it.polimi.telco.ejb.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Table(name = "Subscription", indexes = {
        @Index(name = "FK_Subscription_ServicePackage_idx", columnList = "ServicePackageID")
})
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "StartDate", nullable = false)
    private Date startDate;

    @Column(name = "TotalPrice", precision = 5, scale = 2)
    private BigDecimal totalPrice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ServicePackageID", nullable = false)
    private ServicePackage servicePackageID;

    @Column(name = "PeriodID", nullable = false)
    private Integer periodID;

    public Integer getPeriodID() {
        return periodID;
    }

    public void setPeriodID(Integer periodID) {
        this.periodID = periodID;
    }

    public ServicePackage getServicePackageID() {
        return servicePackageID;
    }

    public void setServicePackageID(ServicePackage servicePackageID) {
        this.servicePackageID = servicePackageID;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Set<Product> getProductChosen() {
        return productChosen;
    }

    public void setProductChosen(Set<Product> productChosen) {
        this.productChosen = productChosen;
    }

    @JoinTable(name = "SOP", joinColumns = @JoinColumn(name = "SubscriptionID"),
            inverseJoinColumns = @JoinColumn(name = "OptionalProductID"))
    @ManyToMany
    private Set<Product> productChosen;
}