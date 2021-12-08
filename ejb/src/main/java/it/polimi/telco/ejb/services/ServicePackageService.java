package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.ServicePackage;
import it.polimi.telco.ejb.entities.User;
import it.polimi.telco.ejb.exceptions.CredentialsException;
import it.polimi.telco.ejb.exceptions.NoServicePackageFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

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
}
