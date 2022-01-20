package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.Review;
import it.polimi.telco.ejb.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Stateless(name = "ReviewServiceEJB")
public class ReviewService {

    @PersistenceContext(unitName = "telco_persistence")
    private EntityManager em;

    public ReviewService() {}


    public Review prepareReview(User user, BigDecimal amount){

        Review review = new Review();

        review.setUserID(user);
        review.setEmail(user.getEmail());
        review.setAmount(amount);


        Instant instant = Instant.now();
        Timestamp timestamp = Timestamp.from(instant);

        review.setLastRejection(timestamp);

        return review;

    }

    public Integer submit(Review review) {

        em.persist(review);

        em.flush();

        return review.getId();
    }

}
