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
import org.thymeleaf.context.WebContext;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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
    public void init() throws ServletException {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        Order order = (Order) request.getAttribute("order");

        boolean positiveCheck = ExternalService.checkValidation(null);

        if(!positiveCheck){

            // increment attempt
            int attempt = orderService.incrementAttempt(order);

            if(attempt >= 3){

                // add user to review list
                Review review = reviewService.prepareReview(user, order.getSubscriptionID().getTotalPrice());
                reviewService.submit(review);
            }
        }
        else {
            orderService.orderPaid(order);
        }

        response.sendRedirect(getServletContext().getContextPath() + "/GoToHome");

    }


    private void invalidParameter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("errorMessage", "Invalid parameter");
        templateEngine.process("/WEB-INF/home.html", ctx, response.getWriter());
    }
}
