package it.polimi.telco.admin.controllers;

import it.polimi.telco.admin.utils.ThymeleafFactory;
import it.polimi.telco.ejb.entities.Employee;
import it.polimi.telco.ejb.exceptions.CredentialsException;
import it.polimi.telco.ejb.services.EmployeeService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckLogin", value = "/CheckLogin")
public class CheckLogin extends HttpServlet {

    private TemplateEngine templateEngine;


    @EJB(name = "EmployeeServiceEJB")
    private EmployeeService employeeService;


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

        Employee employee;
        try {
            // query db to authenticate for user
            employee = employeeService.checkCredentials(usr, pwd);
        } catch (CredentialsException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
            return;
        }

        // If the user exists, add info to the session and go to home page, otherwise
        // show login page with error message

        if (employee == null) {
            invalidCredentials(request, response);
            return;
        }



        request.getSession().setAttribute("employee", employee);

        response.sendRedirect(getServletContext().getContextPath() + "/GoToHome");

    }

    private void invalidCredentials(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("errorMessage", "Incorrect username or password");
        templateEngine.process("/index.html", ctx, response.getWriter());
    }
}
