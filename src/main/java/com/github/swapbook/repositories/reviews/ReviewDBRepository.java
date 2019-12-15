package com.github.swapbook.repositories.reviews;

import com.github.swapbook.model.Review;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("swapbook.reviews")
public class ReviewDBRepository implements ReviewRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public ReviewDBRepository() {
    }

    @Override
    public List<Review> getReviewsForBook(int book_id) {
        return entityManager.createNativeQuery("select * from swapbook.reviews where book_id = ?", Review.class)
                .setParameter(1, book_id)
                .getResultList();
    }

    @Override
    public List<Review> getAllReviews() {
        return entityManager.createNativeQuery("select * from swapbook.reviews", Review.class).getResultList();
    }

    @Override
    public Review getReviewById(int review_id) {
        return ((Review) entityManager.createNativeQuery("select * from swapbook.reviews WHERE review_id=?", Review.class)
                .setParameter(1, review_id)
                .getSingleResult());
    }

    @Override
    @Transactional
    public void addReview(Review review) {
        entityManager.createNativeQuery("INSERT INTO swapbook.reviews VALUES (?,?,?,?,?,?)")
                .setParameter(1, review.getReview_id())
                .setParameter(2, review.getBook_id())
                .setParameter(3, review.getUser_id())
                .setParameter(4, review.getText())
                .setParameter(5, review.getMark())
                .setParameter(6, review.getDate())
                .executeUpdate();

    }

    @Override
    @Transactional
    public void deleteReviewById(int reviev_id) {
        entityManager.createNativeQuery("delete from swapbook.reviews WHERE  review_id=?", Review.class)
                .setParameter(1, reviev_id)
                .executeUpdate();
    }
}

