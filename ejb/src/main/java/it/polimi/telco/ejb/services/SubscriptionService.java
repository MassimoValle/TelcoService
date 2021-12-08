package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.Product;
import it.polimi.telco.ejb.entities.ServicePackage;
import it.polimi.telco.ejb.entities.Subscription;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.Set;

@Stateful(name = "SubscriptionServiceEJB")
public class SubscriptionService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public SubscriptionService() {}

    public Subscription prepareSubscription(ServicePackage servicePackage, int period, Date date, Set<Product> productsChosen){

        Subscription subscription = new Subscription();

        subscription.setServicePackageID(servicePackage);
        subscription.setPeriodID(period);
        subscription.setStartDate(date);
        subscription.setProductChosen(productsChosen);

        return subscription;

    }

    public Integer submit(Subscription subscription) {

        em.persist(subscription);

        em.flush();

        return subscription.getId();
    }


    public Subscription getSubscriptionById(int id) {
        Subscription subscription = em.find(Subscription.class, id);

        if (subscription == null) {
            return null;
        }

        em.refresh(subscription);

        return subscription;
    }

}
