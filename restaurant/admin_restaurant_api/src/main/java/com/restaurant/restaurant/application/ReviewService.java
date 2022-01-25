package com.restaurant.restaurant.application;

import com.restaurant.restaurant.domain.Review;
import com.restaurant.restaurant.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviews() {
        return (List<Review>) reviewRepository.findAll();
    }
}
