package com.example.onlineStore.controllers;

import com.example.onlineStore.dto.AddressDto;
import com.example.onlineStore.dto.ReviewDto;
import com.example.onlineStore.entities.Address;
import com.example.onlineStore.entities.Review;
import com.example.onlineStore.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/create")
    public  ResponseEntity<?> createReview(@RequestBody ReviewDto reviewDto){
        Review review = reviewService.createReview(reviewDto);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews(){
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id){
        Review review = reviewService.getReviewById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
}