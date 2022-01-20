package it.polimi.telco.web.controllers;

import it.polimi.telco.ejb.entities.Order;
import it.polimi.telco.ejb.entities.Subscription;
import it.polimi.telco.ejb.entities.User;
import it.polimi.telco.ejb.exceptions.OrderException;
import it.polimi.telco.ejb.services.OrderService;
import it.polimi.telco.ejb.services.SubscriptionService;
import it.polimi.telco.ejb.services.UserService;
import it.polimi.telco.web.utils.ThymeleafFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void init() {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // coming from confirmPage.html

        // getting params
        int idSubscription = Integer.parseInt(request.getParameter("idSubscription"));
        String paymentAccepted = request.getParameter("paymentAccepted");
        String paymentRejected = request.getParameter("paymentRejected");

        Boolean payment;
        if(paymentAccepted != null && paymentAccepted.equals("on")){
            payment = true;
        }else if(paymentRejected != null && paymentRejected.equals("on")) {
            payment = false;
        }
        else payment = null;

        Subscription subscription = subscriptionService.getSubscriptionById(idSubscription);
        User user = (User) request.getSession().getAttribute("user");


        // creating order
        Order order = null;

        // check if order already exists
        try {
            order = orderService.getOrder(user, subscription);
        }
        catch (OrderException orderException){
            invalidParameter(request, response);
        }

        // if not, create it
        if(order == null){

            order = orderService.prepareOrder(user, subscription);
            orderService.submit(order);
        }


        // forward to CheckPayment servlet

        request.setAttribute("order", order);
        request.setAttribute("payment", payment);

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
