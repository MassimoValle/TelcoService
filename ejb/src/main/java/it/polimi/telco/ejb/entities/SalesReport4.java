package it.polimi.telco.ejb.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@NamedQueries({
        @NamedQuery(name = "SalesReport4.getAll",
                query = "SELECT p FROM SalesReport4 p")
})

@Entity
@Table(name = "Sales_Report_4")
public class SalesReport4 {
    @Id
    @Column(name = "ServicePackage", nullable = false, length = 45)
    private String id;

    @Column(name = "AverageNumberOfOptionalProducts", precision = 5, scale = 2)
    private BigDecimal averageNumberOfOptionalProducts;

    public BigDecimal getAverageNumberOfOptionalProducts() {
        return averageNumberOfOptionalProducts;
    }

    public void setAverageNumberOfOptionalProducts(BigDecimal averageNumberOfOptionalProducts) {
        this.averageNumberOfOptionalProducts = averageNumberOfOptionalProducts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}