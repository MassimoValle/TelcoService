package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.SalesReport5Insolventuser;
import it.polimi.telco.ejb.entities.SalesReport5Suspendedorder;
import it.polimi.telco.ejb.exceptions.NoProductFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless(name = "SalesReport5SuspendedorderServiceEJB")
public class SalesReport5SuspendedorderService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;


    public SalesReport5SuspendedorderService(){}


    public List<SalesReport5Suspendedorder> getAll() throws PersistenceException {

        List<SalesReport5Suspendedorder> salesReport5Suspendedorders = null;
        try {
            salesReport5Suspendedorders = em.createNamedQuery("SalesReport5Suspendedorder.getAll", SalesReport5Suspendedorder.class)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new PersistenceException("No report2s Found");
        }

        return salesReport5Suspendedorders;
    }
}
