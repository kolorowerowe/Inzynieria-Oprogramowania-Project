package com.github.swapbook.repositories.reviews;

import com.github.swapbook.model.Review;

import java.util.List;

public interface ReviewRepository {
    List<Review> getReviewsForBook(int book_id);
    List<Review> getAllReviews();
    Review getReviewById(int reviev_id);
    void addReview(Review review);
    void deleteReviewById(int reviev_id);

}
