package com.github.swapbook.repositories.reviews;

import com.github.swapbook.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService implements ReviewServiceI {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Review> getReviewsForBook(int book_id) {
        return reviewRepository.findAll().stream().filter(b -> b.getBook_id()==book_id).collect(Collectors.toList());
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(int reviev_id) {
        return reviewRepository.findById(reviev_id).orElse(null);
    }

    @Override
    public void addReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void deleteReviewById(int reviev_id) {
        reviewRepository.deleteById(reviev_id);
    }
}
