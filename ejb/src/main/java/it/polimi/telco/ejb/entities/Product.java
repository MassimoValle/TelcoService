package it.polimi.telco.ejb.entities;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "Product.getAll",
                query = "SELECT p FROM Product p")
})

@Entity
public class Product {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "Description", nullable = false, length = 200)
    private String description;

    @Column(name = "MonthlyFee", nullable = false)
    private Integer monthlyFee;

    public Integer getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Integer monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "possibleProductsToAdd")
    private Set<ServicePackage> packagesUseIt;

    @ManyToMany(mappedBy = "productChosen")
    private Set<Subscription> subscriptionsUseIt;
}