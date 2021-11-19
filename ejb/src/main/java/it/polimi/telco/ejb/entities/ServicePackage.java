package it.polimi.telco.ejb.entities;

import javax.persistence.*;
import java.util.Set;

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

    public Set<Service> getServicesInPackage() {
        return servicesInPackage;
    }

    @JoinTable(name = "SPS", joinColumns = @JoinColumn(name = "IDServicePackage"),
                                inverseJoinColumns = @JoinColumn(name = "IDService"))
    @ManyToMany
    private Set<Service> servicesInPackage;
}