package com.github.swapbook.api;

import com.github.swapbook.model.Review;
import com.github.swapbook.repositories.reviews.ReviewService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReviewsTest {

    private MockMvc mockMvc;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private Reviews reviews;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(reviews).build();
    }

    @Test
    public void getAllReviews_shouldReturnEmptyList() throws Exception {
        List<Review> reviews = new LinkedList<>();

        when(reviewService.getAllReviews()).thenReturn(reviews);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/reviews/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0)));
    }

    @Test
    public void getAllReviews_shouldReturnListWithOneElement() throws Exception {

        List<Review> reviews = new LinkedList<>();
        Review review1 = new Review();
        review1.setText("Fajnie");
        review1.setMark(3);
        reviews.add(review1);

        when(reviewService.getAllReviews()).thenReturn(reviews);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/reviews/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.[0].*", Matchers.hasSize(6)))
                .andExpect(jsonPath("$.[0].mark", Matchers.is(review1.getMark())))
                .andExpect(jsonPath("$.[0].text", Matchers.is(review1.getText())));
    }

    @Test
    public void getReviewById() throws Exception {
        List<Review> reviews = new LinkedList<>();
        Review review1 = new Review();
        review1.setText("Fajnie");
        review1.setMark(3);
        reviews.add(review1);

        when(reviewService.getReviewById(5)).thenReturn(review1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/reviews/5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(6)))
                .andExpect(jsonPath("$.mark", Matchers.is(review1.getMark())))
                .andExpect(jsonPath("$.text", Matchers.is(review1.getText())));
    }
}