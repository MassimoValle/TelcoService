package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.SalesReport2;
import it.polimi.telco.ejb.entities.SalesReport5Insolventuser;
import it.polimi.telco.ejb.exceptions.NoProductFoundException;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless(name = "SalesReport5InsolventusersServiceEJB")
public class SalesReport5InsolventusersService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public SalesReport5InsolventusersService(){}

    public List<SalesReport5Insolventuser> getAll() throws NoProductFoundException {

        List<SalesReport5Insolventuser> salesReport5Insolventusers = null;
        try {
            salesReport5Insolventusers = em.createNamedQuery("SalesReport5Insolventuser.getAll", SalesReport5Insolventuser.class)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new NoProductFoundException("No report2s Found");
        }

        return salesReport5Insolventusers;
    }
}
