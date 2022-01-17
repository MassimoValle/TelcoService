package it.polimi.telco.ejb.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "SalesReport5Insolventuser.getAll",
                query = "SELECT p FROM SalesReport5Insolventuser p")
})

@Entity
@Table(name = "Sales_Report_5_InsolventUsers")
public class SalesReport5Insolventuser {
    @Id
    @Column(name = "User", nullable = false, length = 45)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}