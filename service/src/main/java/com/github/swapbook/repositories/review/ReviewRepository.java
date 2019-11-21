package com.github.swapbook.repositories.review;

import com.github.swapbook.model.Review;

import java.util.List;

public interface ReviewRepository {
    List<Review> getReviewsFromBook(int bookId);
    void addReviewToBook(int bookId, Review review);
    List<Review> getAllReviews();
    void addReview(Review review);

}
