package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.Order;
import it.polimi.telco.ejb.entities.Subscription;
import it.polimi.telco.ejb.entities.User;
import it.polimi.telco.ejb.exceptions.OrderException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless(name = "OrderServiceEJB")
public class OrderService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public OrderService() {}

    public List<Order> getRejected(User user) throws OrderException {
        List<Order> rejected = null;
        try {
            rejected = em.createNamedQuery("Order.getRejectedOrders", Order.class)
                    .setParameter("usr", user)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new OrderException("Could not get rejected orders");
        }

        return rejected;
    }

    public Order getOrder(User user, Subscription subscription) throws OrderException{

        List<Order> orders;
        try {
            orders = em.createNamedQuery("Order.getOrder", Order.class)
                    .setParameter("usr", user)
                    .setParameter("sbcr", subscription)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new OrderException("Could not get rejected orders");
        }

        if(orders.isEmpty())
            return null;

        return orders.get(0);

    }

    public Order prepareOrder(User user, Subscription subscription){

        Order order = new Order();

        order.setUserID(user);
        order.setSubscriptionID(subscription);

        return order;

    }

    public int incrementAttempt(Order order){

        int attempt = order.getAttempt();
        attempt++;
        order.setAttempt(attempt);

        order.setStatus("insolvent");

        em.merge(order);

        return attempt;
    }

    public void orderPaid(Order order){

        order.setStatus("paid");

        em.merge(order);
    }

    public void submit(Order order) {

        em.persist(order);

        em.flush();
    }
}
