package it.polimi.telco.web.controllers;

import it.polimi.telco.ejb.entities.Subscription;
import it.polimi.telco.ejb.services.SubscriptionService;
import it.polimi.telco.web.utils.ThymeleafFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfirmSubscription", value = "/ConfirmSubscription")
public class ConfirmSubscription extends HttpServlet {

    private TemplateEngine templateEngine;

    @EJB(name = "SubscriptionServiceEJB")
    private SubscriptionService subscriptionService;

    @Override
    public void init() {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // coming from SaveSubscription (default flow) or CheckLogin (user not logged) servlets or from home page (payment failed)


                                    // if request come from rejectedOrder in home page
        Integer idSubscription = (Integer) request.getAttribute("idSubscription");

                                    // if request forward from SaveSubscription or CheckLogin
        if(idSubscription == null) idSubscription = Integer.parseInt(request.getParameter("idSubscription"));


        // getting variables to fill up the html
        Subscription subscription = subscriptionService.getSubscriptionById(idSubscription);
        boolean logged = request.getSession().getAttribute("user") != null;


        // adding variables to servlet context
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("idSubscription", idSubscription);
        ctx.setVariable("servicePackage", subscription.getServicePackageID());
        ctx.setVariable("period", subscription.getPeriodID());
        ctx.setVariable("productsChosen", subscription.getProductChosen());
        ctx.setVariable("date", subscription.getStartDate());
        ctx.setVariable("totalPrice", subscription.getTotalPrice());
        ctx.setVariable("logged", logged);


        // used like cart if user is not logged
        request.getSession().setAttribute("idSubscription", idSubscription);

        templateEngine.process("/WEB-INF/confirmationPage", ctx, response.getWriter());

    }

}
