package it.polimi.telco.web.controllers;

import it.polimi.telco.ejb.entities.Order;
import it.polimi.telco.ejb.entities.Review;
import it.polimi.telco.ejb.entities.User;
import it.polimi.telco.ejb.services.OrderService;
import it.polimi.telco.ejb.services.ReviewService;
import it.polimi.telco.ejb.services.SubscriptionService;
import it.polimi.telco.ejb.services.UserService;
import it.polimi.telco.web.utils.ExternalService;
import it.polimi.telco.web.utils.ThymeleafFactory;
import org.thymeleaf.TemplateEngine;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckPayment", value = "/CheckPayment")
public class CheckPayment extends HttpServlet {

    private TemplateEngine templateEngine;

    @EJB(name = "UserServiceEJB")
    private UserService userService;

    @EJB(name = "SubscriptionServiceEJB")
    private SubscriptionService subscriptionService;

    @EJB(name = "OrderServiceEJB")
    private OrderService orderService;

    @EJB(name = "ReviewServiceEJB")
    private ReviewService reviewService;

    @Override
    public void init() {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // coming from PlaceOrder servlet

        User user = (User) request.getSession().getAttribute("user");
        Order order = (Order) request.getAttribute("order");
        Boolean payment = (Boolean) request.getAttribute("payment");


        // payment to external service
        boolean positiveCheck = ExternalService.checkValidation(payment);


        // if the payment fails
        if(!positiveCheck){

            userService.setStatus(user, "insolvent");



            // increment attempt
            int attempt = orderService.incrementAttempt(order);



            if(attempt == 3){

                Review review;

                review = reviewService.prepareReview(user, order.getSubscriptionID().getTotalPrice());
                reviewService.submit(review);

            }
        }


        // else if the payment is successful
        else {
            orderService.orderPaid(order);
        }



        // redirect to GoToHome servlet
        response.sendRedirect(getServletContext().getContextPath() + "/GoToHome");

    }


}
