package it.polimi.telco.admin.controllers;

import it.polimi.telco.admin.utils.ThymeleafFactory;
import it.polimi.telco.ejb.entities.*;
import it.polimi.telco.ejb.exceptions.NoProductFoundException;
import it.polimi.telco.ejb.services.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GoToSalesReport", value = "/GoToSalesReport")
public class GoToSalesReport extends HttpServlet {

    private TemplateEngine templateEngine;

    @EJB(name = "SalesReport1ServiceEJB")
    private SalesReport1Service salesReport1Service;

    @EJB(name = "SalesReport2ServiceEJB")
    private SalesReport2Service salesReport2Service;

    @EJB(name = "SalesReport3ServiceEJB")
    private SalesReport3Service salesReport3Service;

    @EJB(name = "SalesReport4ServiceEJB")
    private SalesReport4Service salesReport4Service;

    @EJB(name = "SalesReport5InsolventusersServiceEJB")
    private SalesReport5InsolventusersService salesReport5InsolventusersService;

    @EJB(name = "SalesReport5SuspendedorderServiceeEJB")
    private SalesReport5SuspendedorderService salesReport5SuspendedorderService;

    @EJB(name = "SalesReport5AlertServiceServiceeEJB")
    private SalesReport5AlertService salesReport5AlertService;

    @EJB(name = "SalesReport6ServiceEJB")
    private SalesReport6Service salesReport6Service;

    @Override
    public void init() throws ServletException {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Number of total purchases per package
        List<SalesReport1> salesReport1s = null;
        try {
            salesReport1s = salesReport1Service.getAll();
        } catch (NoProductFoundException e) {
            e.printStackTrace();
        }

        // Number of total purchases per package and validity period.
        List<SalesReport2> salesReport2s = null;
        try {
            salesReport2s = salesReport2Service.getAll();
        } catch (NoProductFoundException e) {
            e.printStackTrace();
        }

        // Total value of sales per package with and without the optional products.
        List<SalesReport3> salesReport3s = null;
        try {
            salesReport3s = salesReport3Service.getAll();
        } catch (NoProductFoundException e) {
            e.printStackTrace();
        }

        // Average number of optional products sold together with each service package.
        List<SalesReport4> salesReport4s = null;
        try {
            salesReport4s = salesReport4Service.getAll();
        } catch (NoProductFoundException e) {
            e.printStackTrace();
        }

        // List of insolvent users, suspended orders and alerts.
        List<SalesReport5Insolventuser> salesReport5Insolventusers = null;
        try {
            salesReport5Insolventusers = salesReport5InsolventusersService.getAll();
        } catch (NoProductFoundException e) {
            e.printStackTrace();
        }

        List<SalesReport5Suspendedorder> salesReport5Suspendedorders = null;
        try {
            salesReport5Suspendedorders = salesReport5SuspendedorderService.getAll();
        } catch (NoProductFoundException e) {
            e.printStackTrace();
        }

        List<SalesReport5Alert> salesReport5Alerts = null;
        try {
            salesReport5Alerts = salesReport5AlertService.getAll();
        } catch (NoProductFoundException e) {
            e.printStackTrace();
        }

        // Best seller optional product, i.e. the optional product with the greatest value of sales across all the sold service packages.
        List<SalesReport6> salesReport6s = null;
        try {
            salesReport6s = salesReport6Service.getAll();
        } catch (NoProductFoundException e) {
            e.printStackTrace();
        }


        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("salesReport1s", salesReport1s);
        ctx.setVariable("salesReport2s", salesReport2s);
        ctx.setVariable("salesReport3s", salesReport3s);
        ctx.setVariable("salesReport4s", salesReport4s);
        ctx.setVariable("salesReport5Insolventusers", salesReport5Insolventusers);
        ctx.setVariable("salesReport5Suspendedorders", salesReport5Suspendedorders);
        ctx.setVariable("salesReport5Alerts", salesReport5Alerts);
        ctx.setVariable("salesReport6s", salesReport6s);

        templateEngine.process("/WEB-INF/salesReport.html", ctx, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
