package it.polimi.telco.web.controllers;

import it.polimi.telco.ejb.entities.*;
import it.polimi.telco.ejb.exceptions.OrderException;
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

    private TemplateEngine templateEngine;

    @EJB(name = "UserServiceEJB")
    private UserService userService;

    @EJB(name = "SubscriptionServiceEJB")
    private SubscriptionService subscriptionService;

    @EJB(name = "OrderServiceEJB")
    private OrderService orderService;


    @Override
    public void init() throws ServletException {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer idSubscription = Integer.parseInt(request.getParameter("idSubscription"));

        Subscription subscription = subscriptionService.getSubscriptionById(idSubscription);
        User user = (User) request.getSession().getAttribute("user");

        Order order = null;

        try {
            order = orderService.getOrder(user, subscription);
        }
        catch (OrderException orderException){
            invalidParameter(request, response);
        }

        if(order == null){

            order = orderService.prepareOrder(user, subscription);
            orderService.submit(order);
        }

        request.setAttribute("order", order);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CheckPayment");
        dispatcher.forward(request, response);

    }

    private void invalidParameter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("errorMessage", "Invalid parameter");
        templateEngine.process("/WEB-INF/home.html", ctx, response.getWriter());
    }

}
