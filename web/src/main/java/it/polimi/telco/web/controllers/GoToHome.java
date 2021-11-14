package it.polimi.telco.web.controllers;

import it.polimi.telco.ejb.entities.ServicePackage;
import it.polimi.telco.ejb.entities.User;
import it.polimi.telco.ejb.exceptions.CredentialsException;
import it.polimi.telco.ejb.exceptions.NoServicePackageFoundException;
import it.polimi.telco.ejb.services.ServicePackageService;
import it.polimi.telco.ejb.services.UserService;
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

    @Override
    public void init() throws ServletException {
        this.templateEngine = ThymeleafFactory.create(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<ServicePackage> servicePackages;

        try {
            // query db to authenticate for user
            servicePackages = servicePackageService.getServicePackages();
        } catch (NoServicePackageFoundException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
            return;
        }

        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("servicePackages", servicePackages);

        templateEngine.process("/WEB-INF/home.html", ctx, response.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
