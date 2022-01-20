package it.polimi.telco.web.controllers;

import it.polimi.telco.ejb.entities.*;
import it.polimi.telco.ejb.exceptions.NoPeriodFoundException;
import it.polimi.telco.ejb.services.PeriodService;
import it.polimi.telco.ejb.services.ServicePackageService;
import it.polimi.telco.web.utils.ThymeleafFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BuildSubscription", value = "/BuildSubscription")
public class BuildSubscription extends HttpServlet {


    private TemplateEngine templateEngine;


    @EJB(name = "ServicePackageServiceEJB")
    private ServicePackageService servicePackageService;

    @EJB(name = "PeriodServiceEJB")
    private PeriodService periodService;

    @Override
    public void init() {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // coming from home.html
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());



        // get name of the package I want to buy
        Integer servicePackageId = Integer.parseInt(request.getParameter("servicePackageId"));


        // get the package using package's name
        ServicePackage servicePackage = servicePackageService.getServicePackageById(servicePackageId);

        // getting periods
        List<Period> periods;

        try {

            periods = periodService.getAllPeriods();
        } catch (NoPeriodFoundException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not get periods");
            return;
        }


        // !!! I dont need to get products because them are directly in servicePackage.getPossibleProductsToAdd()

        ctx.setVariable("servicePackage", servicePackage);
        ctx.setVariable("periods", periods);

        templateEngine.process("/WEB-INF/buildSubscription.html", ctx, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

}
