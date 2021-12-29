package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.Period;
import it.polimi.telco.ejb.entities.Product;
import it.polimi.telco.ejb.exceptions.NoPeriodFoundException;
import it.polimi.telco.ejb.exceptions.NoProductFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless(name = "PeriodServiceEJB")
public class PeriodService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public PeriodService() {}


    public List<Period> getAllPeriods() throws NoPeriodFoundException{


        List<Period> periods = null;
        try {
            periods = em.createNamedQuery("Period.getAll", Period.class)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new NoPeriodFoundException("No periods Found");
        }

        return periods;
    }
}
