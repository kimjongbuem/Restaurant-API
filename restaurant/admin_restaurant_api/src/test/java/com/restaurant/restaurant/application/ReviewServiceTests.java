package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.Review;
import com.restaurant.restaurant.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

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
    public void getReviews(){
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder().score(3).build());
        when(reviewService.getReviews()).thenReturn(reviews);
        reviews = (List<Review>) reviewRepository.findAll();
        assertThat(reviews.get(0).getScore(),is(3));
    }
}