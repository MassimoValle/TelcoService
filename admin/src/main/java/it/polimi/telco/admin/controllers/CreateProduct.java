package it.polimi.telco.admin.controllers;

import it.polimi.telco.ejb.entities.Product;
import it.polimi.telco.ejb.entities.ServicePackage;
import it.polimi.telco.ejb.services.ProductService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateProduct", value = "/CreateProduct")
public class CreateProduct extends HttpServlet {

    @EJB(name = "ProductServiceEJB")
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String productDescription = request.getParameter("productDescription");
        Integer monthlyFee = Integer.parseInt(request.getParameter("monthlyFee"));


        Product product = productService.prepareProduct(productDescription, monthlyFee);
        productService.submit(product);


        response.sendRedirect(getServletContext().getContextPath() + "/GoToHome");

    }
}
