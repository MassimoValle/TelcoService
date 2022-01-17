package it.polimi.telco.ejb.entities;

import javax.persistence.*;


@NamedQueries({
        @NamedQuery(name = "SalesReport2.getAll",
                query = "SELECT p FROM SalesReport2 p")
})


@Entity
@Table(name = "Sales_Report_2")
public class SalesReport2 {
    @EmbeddedId
    private SalesReport2Id id;

    @Column(name = "NumberTotalPurchasesPerPackageAndValidityPeriod")
    private Integer numberTotalPurchasesPerPackageAndValidityPeriod;

    public Integer getNumberTotalPurchasesPerPackageAndValidityPeriod() {
        return numberTotalPurchasesPerPackageAndValidityPeriod;
    }

    public void setNumberTotalPurchasesPerPackageAndValidityPeriod(Integer numberTotalPurchasesPerPackageAndValidityPeriod) {
        this.numberTotalPurchasesPerPackageAndValidityPeriod = numberTotalPurchasesPerPackageAndValidityPeriod;
    }

    public SalesReport2Id getId() {
        return id;
    }

    public void setId(SalesReport2Id id) {
        this.id = id;
    }
}