package it.polimi.telco.web.controllers;

import it.polimi.telco.ejb.entities.User;
import it.polimi.telco.ejb.exceptions.CredentialsException;
import it.polimi.telco.ejb.services.SubscriptionService;
import it.polimi.telco.ejb.services.UserService;
import it.polimi.telco.web.utils.ThymeleafFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CheckLogin", value = "/CheckLogin")
public class CheckLogin extends HttpServlet {

    private TemplateEngine templateEngine;

    @EJB(name = "UserServiceEJB")
    private UserService userService;

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

        // obtain params
        String usr = request.getParameter("username");
        String pwd = request.getParameter("password");
        if (usr == null || pwd == null) {
            invalidCredentials(request, response);
            return;
        }

        usr = usr.trim();
        pwd = pwd.trim();
        if (usr.isEmpty() || pwd.isEmpty()) {
            invalidCredentials(request, response);
            return;
        }

        User user;
        try {
            // query db to authenticate for user
            user = userService.checkCredentials(usr, pwd);
        } catch (CredentialsException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
            return;
        }

        // If the user exists, add info to the session and go to home page, otherwise
        // show login page with error message

        if (user == null) {
            invalidCredentials(request, response);
            return;
        }

        request.getSession().setAttribute("user", user);
        Integer idSubscription = (Integer) request.getSession().getAttribute("idSubscription");


        if(idSubscription == null)
            response.sendRedirect(getServletContext().getContextPath() + "/GoToHome");

        else{
            request.setAttribute("idSubscription", idSubscription);
            request.getSession().removeAttribute("idSubscription");

            RequestDispatcher rd = request.getRequestDispatcher("/ConfirmSubscription");
            rd.forward(request, response);
        }

    }

    private void invalidCredentials(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("errorMessage", "Incorrect username or password");
        templateEngine.process("/index.html", ctx, response.getWriter());
    }
}
