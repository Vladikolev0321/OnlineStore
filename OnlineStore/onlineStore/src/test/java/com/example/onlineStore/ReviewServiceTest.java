package com.example.onlineStore;

import com.example.onlineStore.dto.ReviewDto;
import com.example.onlineStore.entities.Review;
import com.example.onlineStore.mappers.ReviewMapper;
import com.example.onlineStore.repositories.ReviewRepo;
import com.example.onlineStore.services.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    private ReviewRepo reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void createReview_ShouldReturnSavedReview() {
        ReviewDto reviewDto = new ReviewDto("Great product!", 4.5);
        Review review = new Review();
        review.setComment("Great product!");
        review.setRating(4.5);

        Review savedReview = new Review();
        savedReview.setComment("Great product!");
        savedReview.setRating(4.5);

        when(reviewMapper.convertDtoToEntity(reviewDto)).thenReturn(review);
        when(reviewRepository.save(review)).thenReturn(savedReview);

        Review result = reviewService.createReview(reviewDto);

        assertNotNull(result);
        assertEquals(savedReview, result);
        assertEquals("Great product!", result.getComment());
        assertEquals(4.5, result.getRating());
        verify(reviewMapper).convertDtoToEntity(reviewDto);
        verify(reviewRepository).save(review);
    }

    @Test
    void getAllReviews_ShouldReturnListOfReviews() {
        List<Review> reviews = List.of(new Review(), new Review());

        when(reviewRepository.findAll()).thenReturn(reviews);

        List<Review> result = reviewService.getAllReviews();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(reviewRepository).findAll();
    }

    @Test
    void getReviewById_ShouldReturnReview_WhenExists() {
        long id = 1L;
        Review review = new Review();

        when(reviewRepository.findById(id)).thenReturn(Optional.of(review));

        Review result = reviewService.getReviewById(id);

        assertNotNull(result);
        assertEquals(review, result);
        verify(reviewRepository).findById(id);
    }

    @Test
    void getReviewById_ShouldThrowException_WhenNotFound() {
        long id = 1L;

        when(reviewRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> reviewService.getReviewById(id));
        verify(reviewRepository).findById(id);
    }
}