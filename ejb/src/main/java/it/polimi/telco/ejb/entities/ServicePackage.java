package it.polimi.telco.ejb.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "ServicePackage.getAll",
                query = "SELECT sp FROM ServicePackage sp")
})

@Entity
public class ServicePackage {
    @Id
    @Column(name = "Name", nullable = false, length = 45)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String id) {
        this.name = id;
    }
}