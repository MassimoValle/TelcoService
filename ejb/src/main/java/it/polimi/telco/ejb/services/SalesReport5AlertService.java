package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.SalesReport5Alert;
import it.polimi.telco.ejb.entities.SalesReport5Suspendedorder;
import it.polimi.telco.ejb.exceptions.NoProductFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless(name = "SalesReport5AlertServiceeEJB")
public class SalesReport5AlertService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public SalesReport5AlertService(){}

    public List<SalesReport5Alert> getAll() throws PersistenceException {

        List<SalesReport5Alert> salesReport5Alerts = null;
        try {
            salesReport5Alerts = em.createNamedQuery("SalesReport5Alert.getAll", SalesReport5Alert.class)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new PersistenceException("No report2s Found");
        }

        return salesReport5Alerts;
    }
}
