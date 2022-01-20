package it.polimi.telco.admin.controllers;

import it.polimi.telco.admin.utils.ThymeleafFactory;
import it.polimi.telco.ejb.entities.Period;
import it.polimi.telco.ejb.entities.Product;
import it.polimi.telco.ejb.entities.Service;
import it.polimi.telco.ejb.exceptions.NoPeriodFoundException;
import it.polimi.telco.ejb.exceptions.NoProductFoundException;
import it.polimi.telco.ejb.exceptions.NoServiceFoundException;
import it.polimi.telco.ejb.services.PeriodService;
import it.polimi.telco.ejb.services.ProductService;
import it.polimi.telco.ejb.services.ServiceManagementService;
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
import java.util.List;

@WebServlet(name = "GoToHome", value = "/GoToHome")
public class GoToHome extends HttpServlet {

    private TemplateEngine templateEngine;

    @EJB(name = "ServiceServiceEJB")
    private ServiceManagementService serviceService;

    @EJB(name = "ProductServiceEJB")
    private ProductService productService;

    @EJB(name = "PeriodServiceEJB")
    private PeriodService periodService;

    @Override
    public void init() throws ServletException {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Service> services;
        List<Product> products;

        // getting services
        try {

            services = serviceService.getAllServices();
        } catch (NoServiceFoundException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not get services");
            return;
        }

        // getting products
        try {

            products = productService.getAllProducts();
        } catch (NoProductFoundException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not get products");
            return;
        }


        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("services", services);
        ctx.setVariable("products", products);

        templateEngine.process("/WEB-INF/home.html", ctx, response.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
