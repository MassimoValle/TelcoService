package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.SalesReport4;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless(name = "SalesReport4ServiceEJB")
public class SalesReport4Service {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public SalesReport4Service(){}

    public List<SalesReport4> getAll() throws PersistenceException {

        List<SalesReport4> report4s;
        try {
            report4s = em.createNamedQuery("SalesReport4.getAll", SalesReport4.class)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new PersistenceException("No report2s Found");
        }

        return report4s;
    }
}
