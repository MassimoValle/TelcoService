package it.polimi.telco.ejb.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "SalesReport5Suspendedorder.getAll",
                query = "SELECT p FROM SalesReport5Suspendedorder p")
})


@Entity
@Table(name = "Sales_Report_5_SuspendedOrders")
public class SalesReport5Suspendedorder {
    @Id
    @Column(name = "OrderID", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}