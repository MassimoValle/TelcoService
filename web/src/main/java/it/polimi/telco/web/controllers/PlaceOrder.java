package it.polimi.telco.web.controllers;

import it.polimi.telco.ejb.entities.*;
import it.polimi.telco.ejb.services.OrderService;
import it.polimi.telco.ejb.services.ServicePackageService;
import it.polimi.telco.ejb.services.SubscriptionService;
import it.polimi.telco.ejb.services.UserService;
import it.polimi.telco.web.utils.ThymeleafFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "PlaceOrder", value = "/PlaceOrder")
public class PlaceOrder extends HttpServlet {

    @EJB(name = "UserServiceEJB")
    private UserService userService;

    @EJB(name = "SubscriptionServiceEJB")
    private SubscriptionService subscriptionService;

    @EJB(name = "OrderServiceEJB")
    private OrderService orderService;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer idSubscription = (Integer) request.getSession().getAttribute("idSubscription");
        Subscription subscription = subscriptionService.getSubscriptionById(idSubscription);

        User user = (User) request.getSession().getAttribute("user");

        Order order = orderService.prepareOrder(user, subscription);

        orderService.submit(order);

        request.getSession().removeAttribute("idSubscription");
        request.getSession().setAttribute("order", order);


        response.sendRedirect(getServletContext().getContextPath() + "/checkPayment");

    }

}
