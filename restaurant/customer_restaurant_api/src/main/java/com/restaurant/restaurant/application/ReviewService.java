package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.Review;
import com.restaurant.restaurant.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void addReview(Review review) {
        reviewRepository.save(review);
    }
}
