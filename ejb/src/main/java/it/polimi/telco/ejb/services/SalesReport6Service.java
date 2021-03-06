package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.SalesReport6;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless(name = "SalesReport6ServiceEJB")
public class SalesReport6Service {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;


    public SalesReport6Service(){}


    public List<SalesReport6> getAll() throws PersistenceException {

        List<SalesReport6> report6s;
        try {
            report6s = em.createNamedQuery("SalesReport6.getAll", SalesReport6.class)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new PersistenceException("No report2s Found");
        }

        return report6s;
    }
}
