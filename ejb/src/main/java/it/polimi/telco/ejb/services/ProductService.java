package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.Period;
import it.polimi.telco.ejb.entities.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "ProductServiceEJB")
public class ProductService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public ProductService() {}

    public List<Product> getAllProducts(){
        return em.createNamedQuery("Product.getAll", Product.class)
                .getResultList();
    }
}
