package com.github.swapbook.repositories.reviews;

import com.github.swapbook.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewServiceI {
    List<Review> getReviewsForBook(int book_id);
    List<Review> getAllReviews();
    Review getReviewById(int reviev_id);
    void addReview(Review review);
    void deleteReviewById(int reviev_id);

}
