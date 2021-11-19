package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.Period;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "PeriodServiceEJB")
public class PeriodService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public PeriodService() {}


    public List<Period> getAllPeriods(){
        return em.createNamedQuery("Period.getAll", Period.class)
                .getResultList();
    }
}
