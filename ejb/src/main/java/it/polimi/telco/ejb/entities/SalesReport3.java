package it.polimi.telco.ejb.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@NamedQueries({
        @NamedQuery(name = "SalesReport3.getAll",
                query = "SELECT p FROM SalesReport3 p")
})

@Entity
@Table(name = "Sales_Report_3")
public class SalesReport3 {
    @Id
    @Column(name = "ServicePackage", nullable = false, length = 45)
    private String id;

    @Column(name = "TotalValueOfSales", precision = 5, scale = 2)
    private BigDecimal totalValueOfSales;

    @Column(name = "TotalValueOfSalesWithoutOptionalProducts", precision = 5, scale = 2)
    private BigDecimal totalValueOfSalesWithoutOptionalProducts;

    public BigDecimal getTotalValueOfSalesWithoutOptionalProducts() {
        return totalValueOfSalesWithoutOptionalProducts;
    }

    public void setTotalValueOfSalesWithoutOptionalProducts(BigDecimal totalValueOfSalesWithoutOptionalProducts) {
        this.totalValueOfSalesWithoutOptionalProducts = totalValueOfSalesWithoutOptionalProducts;
    }

    public BigDecimal getTotalValueOfSales() {
        return totalValueOfSales;
    }

    public void setTotalValueOfSales(BigDecimal totalValueOfSales) {
        this.totalValueOfSales = totalValueOfSales;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}