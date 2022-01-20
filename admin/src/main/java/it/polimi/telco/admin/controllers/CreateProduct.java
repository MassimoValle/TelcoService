package it.polimi.telco.admin.controllers;

import it.polimi.telco.ejb.entities.Product;
import it.polimi.telco.ejb.services.ProductService;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "CreateProduct", value = "/CreateProduct")
public class CreateProduct extends HttpServlet {

    @EJB(name = "ProductServiceEJB")
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String productDescription = request.getParameter("productDescription");
        BigDecimal monthlyFee = BigDecimal.valueOf(Double.parseDouble(request.getParameter("monthlyFee")));


        Product product = productService.prepareProduct(productDescription, monthlyFee);
        productService.submit(product);


        response.sendRedirect(getServletContext().getContextPath() + "/GoToHome");

    }
}
