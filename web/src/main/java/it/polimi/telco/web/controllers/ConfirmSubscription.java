package it.polimi.telco.web.controllers;

import it.polimi.telco.ejb.entities.Subscription;
import it.polimi.telco.ejb.entities.User;
import it.polimi.telco.ejb.services.SubscriptionService;
import it.polimi.telco.web.utils.ThymeleafFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
    public void init() throws ServletException {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer idSubscription = (Integer) request.getSession().getAttribute("idSubscription");
        Subscription subscription = subscriptionService.getSubscriptionById(idSubscription);


        boolean logged = request.getSession().getAttribute("user") != null;

        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("servicePackage", subscription.getServicePackageID());
        ctx.setVariable("period", subscription.getPeriodID());
        ctx.setVariable("productsChosen", subscription.getProductChosen());
        ctx.setVariable("date", subscription.getStartDate());
        ctx.setVariable("logged", logged);

        templateEngine.process("/WEB-INF/confirmationPage", ctx, response.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
