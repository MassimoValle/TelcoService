package it.polimi.telco.admin.controllers;

import it.polimi.telco.admin.utils.ThymeleafFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GoToSalesReport", value = "/GoToSalesReport")
public class GoToSalesReport extends HttpServlet {

    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Number of total purchases per package

        // Number of total purchases per package and validity period.

        // Total value of sales per package with and without the optional products.

        // Average number of optional products sold together with each service package.

        // List of insolvent users, suspended orders and alerts.

        // Best seller optional product, i.e. the optional product with the greatest value of sales across all the sold service packages.


        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        // ctx.setVariable("services", services);
        // ctx.setVariable("products", products);

        templateEngine.process("/WEB-INF/home.html", ctx, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
