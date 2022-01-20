package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.Product;
import it.polimi.telco.ejb.entities.SalesReport1;
import it.polimi.telco.ejb.exceptions.NoProductFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless(name = "SalesReport1ServiceEJB")
public class SalesReport1Service {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public SalesReport1Service() {}


    public List<SalesReport1> getAll() throws PersistenceException {

        List<SalesReport1> report1s = null;
        try {
            report1s = em.createNamedQuery("SalesReport1.getAll", SalesReport1.class)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new PersistenceException("No report1s Found");
        }

        return report1s;
    }


}
