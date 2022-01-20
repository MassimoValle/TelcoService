package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.SalesReport2;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless(name = "SalesReport2ServiceEJB")
public class SalesReport2Service {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public SalesReport2Service(){}

    public List<SalesReport2> getAll() throws PersistenceException {

        List<SalesReport2> report2s;
        try {
            report2s = em.createNamedQuery("SalesReport2.getAll", SalesReport2.class)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new PersistenceException("No report2s Found");
        }

        return report2s;
    }
}
