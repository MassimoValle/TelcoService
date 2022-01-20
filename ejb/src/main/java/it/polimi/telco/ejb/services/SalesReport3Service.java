package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.SalesReport3;
import it.polimi.telco.ejb.exceptions.NoProductFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless(name = "SalesReport3ServiceEJB")
public class SalesReport3Service {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public SalesReport3Service(){}

    public List<SalesReport3> getAll() throws PersistenceException {

        List<SalesReport3> report3s = null;
        try {
            report3s = em.createNamedQuery("SalesReport3.getAll", SalesReport3.class)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new PersistenceException("No report2s Found");
        }

        return report3s;
    }
}
