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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "Name", nullable = false, length = 45)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Set<Product> getPossibleProductsToAdd() {
        return possibleProductsToAdd;
    }

    public void setPossibleProductsToAdd(Set<Product> possibleProductsToAdd) {
        this.possibleProductsToAdd = possibleProductsToAdd;
    }

    public Set<Service> getServicesInPackage() {
        return servicesInPackage;
    }

    public void setServicesInPackage(Set<Service> servicesInPackage) {
        this.servicesInPackage = servicesInPackage;
    }

    @JoinTable(name = "SPOP", joinColumns = @JoinColumn(name = "ServicePackageID"),
            inverseJoinColumns = @JoinColumn(name = "OptionalProductID"))
    @ManyToMany
    private Set<Product> possibleProductsToAdd;


    @JoinTable(name = "SPS", joinColumns = @JoinColumn(name = "ServicePackageID"),
            inverseJoinColumns = @JoinColumn(name = "ServiceID"))
    @ManyToMany
    private Set<Service> servicesInPackage;
}