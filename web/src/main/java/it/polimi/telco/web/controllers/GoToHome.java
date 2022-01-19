package it.polimi.telco.web.controllers;

import it.polimi.telco.ejb.entities.Order;
import it.polimi.telco.ejb.entities.ServicePackage;
import it.polimi.telco.ejb.entities.User;
import it.polimi.telco.ejb.exceptions.NoServicePackageFoundException;
import it.polimi.telco.ejb.exceptions.OrderException;
import it.polimi.telco.ejb.services.OrderService;
import it.polimi.telco.ejb.services.ServicePackageService;
import it.polimi.telco.ejb.services.ServiceManagementService;
import it.polimi.telco.web.utils.ThymeleafFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GoToHome", value = "/GoToHome")
public class GoToHome extends HttpServlet {

    private TemplateEngine templateEngine;

    @EJB(name = "ServicePackageServiceEJB")
    private ServicePackageService servicePackageService;

    @EJB(name = "OrderServiceEJB")
    private OrderService orderService;

    @EJB(name = "ServiceServiceEJB")
    private ServiceManagementService serviceManagementService;

    @Override
    public void init() throws ServletException {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // coming from CheckLogin servlet or index.html page (if user click on "skip and go to website")

        User user = (User) request.getSession().getAttribute("user");

        List<ServicePackage> servicePackages;
        List<Order> rejectedOrders = null;

        // getting service packages - always
        try {

            servicePackages = servicePackageService.getServicePackages();
        } catch (NoServicePackageFoundException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
            return;
        }


        // getting rejected order - only if user is logged
        if(user != null){

            try {
                rejectedOrders = orderService.getRejected(user);
            } catch (OrderException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not get rejected orders");
                return;
            }
        }



        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("servicePackages", servicePackages);
        ctx.setVariable("rejectedOrders", rejectedOrders);

        templateEngine.process("/WEB-INF/home.html", ctx, response.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
