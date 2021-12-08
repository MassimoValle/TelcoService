package it.polimi.telco.web.controllers;

import it.polimi.telco.ejb.entities.Period;
import it.polimi.telco.ejb.entities.ServicePackage;
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
    public void init() throws ServletException {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servicePackageName = request.getParameter("servicePackageName");

        if (servicePackageName == null) {
            invalidParameter(request, response);
            return;
        }

        servicePackageName = servicePackageName.trim();
        if (servicePackageName.isEmpty()) {
            invalidParameter(request, response);
            return;
        }

        ServicePackage servicePackage = servicePackageService.getServicePackageById(servicePackageName);

        List<Period> periods = periodService.getAllPeriods();


        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("servicePackage", servicePackage);
        ctx.setVariable("periods", periods);

        templateEngine.process("/WEB-INF/buildSubscription.html", ctx, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void invalidParameter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("errorMessage", "Invalid parameter");
        templateEngine.process("/WEB-INF/buildSubscription.html", ctx, response.getWriter());
    }
}
