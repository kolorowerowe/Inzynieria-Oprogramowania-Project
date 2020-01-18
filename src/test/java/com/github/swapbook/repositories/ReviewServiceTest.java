package com.github.swapbook.repositories;

import com.github.swapbook.model.Review;
import com.github.swapbook.repositories.reviews.ReviewService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Before
    public void setUp() {
        Review review = new Review();
        review.setBook_id(12);
        review.setDate(new Date());
        review.setMark(5);
        review.setText("Fajnie");

    }

    @Test
    public void getAllReviews() {

        Review review = new Review();
        review.setBook_id(12);
        review.setDate(new Date());
        review.setMark(5);
        review.setText("Fajnie");

        Mockito.when(reviewService.getReviewById(12))
                .thenReturn(review);

        Review review2 = reviewService.getReviewById(12);

        assertEquals("Fajnie", review2.getText());
    }
}