package it.polimi.telco.admin.controllers;

import it.polimi.telco.ejb.entities.Product;
import it.polimi.telco.ejb.entities.Service;
import it.polimi.telco.ejb.entities.ServicePackage;
import it.polimi.telco.ejb.exceptions.NoProductFoundException;
import it.polimi.telco.ejb.exceptions.NoServiceFoundException;
import it.polimi.telco.ejb.services.ProductService;
import it.polimi.telco.ejb.services.ServicePackageService;
import it.polimi.telco.ejb.services.ServiceService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "CreatePackage", value = "/CreatePackage")
public class CreatePackage extends HttpServlet {



    @EJB(name = "ServiceServiceEJB")
    private ServiceService serviceService;

    @EJB(name = "ProductServiceEJB")
    private ProductService productService;

    @EJB(name = "ServicePackageServiceEJB")
    private ServicePackageService servicePackageService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String packageName = request.getParameter("packageName");

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

        Set<Service> servicesChosen = null;
        Set<Product> productsChosen = null;

        if(!services.isEmpty()){

            servicesChosen  = new HashSet<>();

            for (Service service : services){

                try {
                    String serviceID = service.getId().toString();

                    String param = request.getParameter("service" + serviceID);

                    if(param == null) continue;
                    if(param.equals("on")) servicesChosen.add(service);

                }catch (NumberFormatException exception){
                    continue;
                }

            }
        }

        if(!products.isEmpty()){

            productsChosen  = new HashSet<>();

            for (Product product : products){

                try {
                    String productID = product.getId().toString();

                    String param = request.getParameter("product" + productID);

                    if(param == null) continue;
                    if(param.equals("on")) productsChosen.add(product);

                }catch (NumberFormatException exception){
                    continue;
                }

            }
        }

        ServicePackage servicePackage = servicePackageService.prepareServicePackage(packageName, servicesChosen, productsChosen);
        servicePackageService.submit(servicePackage);


        response.sendRedirect(getServletContext().getContextPath() + "/GoToHome");

    }
}