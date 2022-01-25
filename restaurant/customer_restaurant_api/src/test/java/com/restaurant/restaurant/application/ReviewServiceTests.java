package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.Review;
import com.restaurant.restaurant.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ReviewServiceTests {

    @Autowired
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReview(){
        reviewService.addReview(
                Review.builder()
                .name("JOKER")
                .description("Great!")
                .build());

        verify(reviewRepository).save(any());
    }
}