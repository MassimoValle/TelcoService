package it.polimi.telco.web.controllers;

import it.polimi.telco.ejb.entities.Period;
import it.polimi.telco.ejb.entities.Product;
import it.polimi.telco.ejb.services.PeriodService;
import it.polimi.telco.ejb.services.ProductService;
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

    @EJB(name = "PeriodServiceEJB")
    private PeriodService periodService;

    @EJB(name = "ProductServiceEJB")
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servicePackages = request.getParameter("servicePackages");

        if (servicePackages == null) {
            invalidParameter(request, response);
            return;
        }

        servicePackages = servicePackages.trim();
        if (servicePackages.isEmpty()) {
            invalidParameter(request, response);
            return;
        }

        List<Period> periods = periodService.getAllPeriods();
        List<Product> products = productService.getAllProducts();


        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("periods", periods);
        ctx.setVariable("products", products);

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
