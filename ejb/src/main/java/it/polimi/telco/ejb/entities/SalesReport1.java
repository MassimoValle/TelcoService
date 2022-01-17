package it.polimi.telco.ejb.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "SalesReport1.getAll",
                query = "SELECT p FROM SalesReport1 p")
})

@Entity
@Table(name = "Sales_Report_1")
public class SalesReport1 {
    @Id
    @Column(name = "ServicePackage", nullable = false, length = 45)
    private String id;

    @Column(name = "NumberTotalPurchases")
    private Integer numberTotalPurchases;

    public Integer getNumberTotalPurchases() {
        return numberTotalPurchases;
    }

    public void setNumberTotalPurchases(Integer numberTotalPurchases) {
        this.numberTotalPurchases = numberTotalPurchases;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}