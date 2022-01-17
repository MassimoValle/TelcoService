package it.polimi.telco.ejb.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "SalesReport6.getAll",
                query = "SELECT p FROM SalesReport6 p")
})


@Entity
@Table(name = "Sales_Report_6")
public class SalesReport6 {
    @Id
    @Column(name = "ProductID", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}