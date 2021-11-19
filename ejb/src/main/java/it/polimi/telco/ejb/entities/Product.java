package it.polimi.telco.ejb.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Product.getAll",
                query = "SELECT p FROM Product p")
})

@Entity
public class Product {
    @Id
    @Column(name = "Name", nullable = false, length = 45)
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String id) {
        this.name = id;
    }
}