package com.example.onlineStore;

import com.example.onlineStore.controllers.ReviewController;
import com.example.onlineStore.dto.ReviewDto;
import com.example.onlineStore.entities.Review;
import com.example.onlineStore.services.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createReview_ShouldReturnCreatedReview() throws Exception {
        ReviewDto reviewDto = new ReviewDto("Great product!", 4.5);
        Review review = new Review();
        review.setId(1L); // Set an ID to verify jsonPath
        review.setComment("Great product!");
        review.setRating(4.5);

        when(reviewService.createReview(any(ReviewDto.class))).thenReturn(review);

        mockMvc.perform(post("/api/reviews/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.comment").value("Great product!"))
                .andExpect(jsonPath("$.rating").value(4.5));

        verify(reviewService).createReview(any(ReviewDto.class));
    }

    @Test
    void getAllReviews_ShouldReturnListOfReviews() throws Exception {
        Review review1 = new Review();
        review1.setId(1L);
        review1.setComment("Review 1");
        review1.setRating(4.0);

        Review review2 = new Review();
        review2.setId(2L);
        review2.setComment("Review 2");
        review2.setRating(5.0);

        List<Review> reviews = List.of(review1, review2);

        when(reviewService.getAllReviews()).thenReturn(reviews);

        mockMvc.perform(get("/api/reviews/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(reviews.size()))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].comment").value("Review 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].comment").value("Review 2"));

        verify(reviewService).getAllReviews();
    }

    @Test
    void getReviewById_ShouldReturnReview_WhenExists() throws Exception {
        long id = 1L;
        Review review = new Review();
        review.setId(id);
        review.setComment("Great review");
        review.setRating(4.5);

        when(reviewService.getReviewById(id)).thenReturn(review);

        mockMvc.perform(get("/api/reviews/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.comment").value("Great review"))
                .andExpect(jsonPath("$.rating").value(4.5));

        verify(reviewService).getReviewById(id);
    }
}

