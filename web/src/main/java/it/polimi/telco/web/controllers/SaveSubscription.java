package it.polimi.telco.web.controllers;

import it.polimi.telco.ejb.entities.Period;
import it.polimi.telco.ejb.entities.Product;
import it.polimi.telco.ejb.entities.ServicePackage;
import it.polimi.telco.ejb.entities.Subscription;
import it.polimi.telco.ejb.services.PeriodService;
import it.polimi.telco.ejb.services.ServicePackageService;
import it.polimi.telco.ejb.services.SubscriptionService;
import it.polimi.telco.web.utils.ThymeleafFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "SaveSubscription", value = "/SaveSubscription")
public class SaveSubscription extends HttpServlet {

    private TemplateEngine templateEngine;

    @EJB(name = "PeriodServiceEJB")
    private PeriodService periodService;

    @EJB(name = "ServicePackageServiceEJB")
    private ServicePackageService servicePackageService;

    @EJB(name = "SubscriptionServiceEJB")
    private SubscriptionService subscriptionService;


    @Override
    public void init() throws ServletException {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // coming from buildSubscription.html

        // getting parameters from html
        String packageName = request.getParameter("packageName");
        int periodID = Integer.parseInt(request.getParameter("period"));
        LocalDate date = LocalDate.parse(request.getParameter("date"));


        // check if params values are correct
        if (packageName == null || date == null) {
            invalidParameter(request, response);
            return;
        }

        packageName = packageName.trim();

        if (packageName.isEmpty()) {
            invalidParameter(request, response);
            return;
        }


        // getting ServicePackage using ServicePackage's name
        ServicePackage servicePackage = servicePackageService.getServicePackageById(packageName);

        Period period = periodService.getPeriod(periodID);


        // getting products chosen by user - ALL THIS BECAUSE COMBOBOX IS DIFFICULT TO MANAGE USING PURE HTML
        Set<Product> productsChosen = null;

        if(servicePackage.getPossibleProductsToAdd() != null){

            productsChosen  = new HashSet<>();

            for (Product product : servicePackage.getPossibleProductsToAdd()){

                try {
                    String productID = product.getId().toString();

                    String param = request.getParameter("product" + productID);

                    if(param == null) continue;
                    if(param.equals("on")) productsChosen.add(product);

                }catch (NumberFormatException ignored){}

            }
        }


        // now, with all params I need, I can create a subscription and submitting it
        Subscription subscription = subscriptionService.prepareSubscription(servicePackage, period, date, productsChosen);
        Integer idSubscription = subscriptionService.submit(subscription);



        // forward to ConfirmSubscription servlet
        request.setAttribute("idSubscription", idSubscription);

        RequestDispatcher rd = request.getRequestDispatcher("/ConfirmSubscription");
        rd.forward(request, response);
    }

    private void invalidParameter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("errorMessage", "Invalid parameter");
        templateEngine.process("/WEB-INF/buildSubscription.html", ctx, response.getWriter());
    }
}
