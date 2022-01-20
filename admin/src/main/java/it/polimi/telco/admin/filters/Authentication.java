package it.polimi.telco.admin.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Authentication")
public class Authentication implements Filter {
    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        // check if user is logged in
        HttpSession session = req.getSession();
        if (session.isNew() || session.getAttribute("employee") == null) {
            res.sendRedirect(req.getServletContext().getContextPath() + "/index.html");
            return;
        }

        chain.doFilter(request, response);
    }
}