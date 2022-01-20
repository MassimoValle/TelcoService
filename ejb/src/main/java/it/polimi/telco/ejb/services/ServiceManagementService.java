package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.Service;
import it.polimi.telco.ejb.exceptions.NoServiceFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless(name = "ServiceServiceEJB")
public class ServiceManagementService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public ServiceManagementService(){}


    public List<Service> getAllServices() throws NoServiceFoundException {
        List<Service> services = null;
        try {
            services = em.createNamedQuery("Service.getAll", Service.class)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new NoServiceFoundException("No service found");
        }


        return services;
    }
}
