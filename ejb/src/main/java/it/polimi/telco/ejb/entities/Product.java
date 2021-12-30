package it.polimi.telco.ejb.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "Product.getAll",
                query = "SELECT p FROM Product p")
})

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "Description", nullable = false, length = 200)
    private String description;

    @Column(name = "MonthlyFee", nullable = false, precision = 5, scale = 2)
    private BigDecimal monthlyFee;

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
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