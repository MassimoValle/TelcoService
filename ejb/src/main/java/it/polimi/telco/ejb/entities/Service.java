package it.polimi.telco.ejb.entities;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "Service.getAll",
                query = "SELECT s FROM Service s")
})

@Entity
public class Service {
    @Id
    @Lob
    @Column(name = "Name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String id) {
        this.name = id;
    }

    @ManyToMany(mappedBy = "servicesInPackage")
    private Set<ServicePackage> packagesUseIt;
}