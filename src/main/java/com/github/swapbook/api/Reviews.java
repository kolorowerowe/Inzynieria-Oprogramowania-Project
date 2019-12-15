package com.github.swapbook.api;

import com.github.swapbook.model.Review;
import com.github.swapbook.repositories.reviews.ReviewDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Reviews {

    @Autowired
    ReviewDBRepository reviewRepository;

    @GetMapping("/api/reviews/all")
    @ResponseBody
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok().body(reviewRepository.getAllReviews());
    }

    @GetMapping("/api/reviews/book/{id}")
    @ResponseBody
    public ResponseEntity<List<Review>> getReviewsForBook(@PathVariable(value = "id") int book_id) {
        return ResponseEntity.ok().body(reviewRepository.getReviewsForBook(book_id));
    }

    @GetMapping("/api/reviews/{id}")
    @ResponseBody
    public ResponseEntity<Review> getReviewById(@PathVariable(value = "id") int reviev_id) {
        return ResponseEntity.ok().body(reviewRepository.getReviewById(reviev_id));
    }


    @PostMapping("/api/reviews/put")
    public void createReview(@RequestBody Review review) {
        reviewRepository.addReview(review);
    }

    @DeleteMapping("/api/reviews/{id}")
    public void deleteReview(@PathVariable(value = "id") int review_id) {
        reviewRepository.deleteReviewById(review_id);
    }
}
