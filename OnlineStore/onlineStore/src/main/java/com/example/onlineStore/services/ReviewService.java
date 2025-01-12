package com.example.onlineStore.services;

import com.example.onlineStore.dto.AddressDto;
import com.example.onlineStore.dto.ReviewDto;
import com.example.onlineStore.entities.Address;
import com.example.onlineStore.entities.Review;
import com.example.onlineStore.mappers.ReviewMapper;
import com.example.onlineStore.repositories.ReviewRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewMapper reviewMapper;
    private  final ReviewRepo reviewRepository;

    @Transactional
    public Review createReview(ReviewDto reviewDto) {
        Review review = reviewMapper.convertDtoToEntity(reviewDto);
        Review savedReview = reviewRepository.save(review);
        log.info("Review created: " + savedReview);
        return savedReview;
    }

    @Transactional
    public List<Review> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews;
    }


    @Transactional
    public Review getReviewById(long id) {
        return reviewRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }
}

