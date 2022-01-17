package it.polimi.telco.ejb.services;

import it.polimi.telco.ejb.entities.Review;
import it.polimi.telco.ejb.entities.User;
import it.polimi.telco.ejb.exceptions.NoReviewFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

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

    public Review getReviewByUser(User user) throws NoReviewFoundException {
        List<Review> reviews = null;
        try {
            reviews = em.createNamedQuery("Review.getByUser", Review.class)
                    .setParameter("usr", user)
                    .getResultList();
        }
        catch (PersistenceException e) {
            throw new NoReviewFoundException("No Review Found");
        }


        if(reviews.isEmpty()) return null;

        return reviews.get(0);
    }
}
