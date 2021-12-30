package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.Product;
import it.polimi.telco.ejb.exceptions.NoProductFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.List;

@Stateless(name = "ProductServiceEJB")
public class ProductService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public ProductService() {}

    public List<Product> getAllProducts() throws NoProductFoundException {

        List<Product> products = null;
        try {
            products = em.createNamedQuery("Product.getAll", Product.class)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new NoProductFoundException("No products Found");
        }

        return products;
    }

    public Product prepareProduct(String productDescription, BigDecimal monthlyFee){

        Product product = new Product();

        product.setDescription(productDescription);
        product.setMonthlyFee(monthlyFee);


        return product;

    }

    public Integer submit(Product product) {

        em.persist(product);

        em.flush();

        return product.getId();
    }
}
