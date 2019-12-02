package com.github.swapbook.repositories.review;

import com.github.swapbook.model.Review;

import java.util.LinkedList;
import java.util.List;

public class FakeReviewRepository implements ReviewRepository{
    private List<Review> reviewList = new LinkedList<>();

    @Override
    public List<Review> getReviewsFromBook(int bookId) {
        return null;
    }

    @Override
    public void addReviewToBook(int bookId, Review review) {

    }

    @Override
    public List<Review> getAllReviews() {
        return reviewList;
    }

    @Override
    public void addReview(Review review) {
        reviewList.add(review);
    }
}
