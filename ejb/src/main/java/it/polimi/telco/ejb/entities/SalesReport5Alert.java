package it.polimi.telco.ejb.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "SalesReport5Alert.getAll",
                query = "SELECT p FROM SalesReport5Alert p")
})


@Entity
@Table(name = "Sales_Report_5_Alerts")
public class SalesReport5Alert {
    @Id
    @Column(name = "ReviewID", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}