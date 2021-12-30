package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.Product;
import it.polimi.telco.ejb.entities.Service;
import it.polimi.telco.ejb.entities.ServicePackage;
import it.polimi.telco.ejb.exceptions.NoServicePackageFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Set;

@Stateless(name = "ServicePackageServiceEJB")
public class ServicePackageService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public ServicePackageService (){}

    public List<ServicePackage> getServicePackages() throws NoServicePackageFoundException {
        List<ServicePackage> servicePackages = null;
        try {
            servicePackages = em.createNamedQuery("ServicePackage.getAll", ServicePackage.class)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new NoServicePackageFoundException("No ServicePackages Found");
        }


        return servicePackages;
    }

    public ServicePackage getServicePackageById(String id) {
        ServicePackage servicePackage = em.find(ServicePackage.class, id);

        if (servicePackage == null) {
            return null;
        }

        em.refresh(servicePackage);

        return servicePackage;
    }

    public ServicePackage prepareServicePackage(String packageName, Set<Service> services, Set<Product> products){

        ServicePackage servicePackage = new ServicePackage();

        servicePackage.setName(packageName);
        servicePackage.setServicesInPackage(services);
        servicePackage.setPossibleProductsToAdd(products);

        return servicePackage;

    }

    public void submit(ServicePackage servicePackage) {

        em.persist(servicePackage);

        em.flush();
    }
}
