package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.Order;
import it.polimi.telco.ejb.entities.Subscription;
import it.polimi.telco.ejb.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "OrderServiceEJB")
public class OrderService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public OrderService() {}

    public Order prepareOrder(User user, Subscription subscription){

        Order order = new Order();

        order.setUserID(user);
        order.setSubscriptionID(subscription);

        return order;

    }

    public void submit(Order order) {

        em.persist(order);

        em.flush();
    }
}
