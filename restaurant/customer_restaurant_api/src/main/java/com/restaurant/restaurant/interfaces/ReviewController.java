package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.ReviewService;
import com.restaurant.restaurant.domain.Review;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> addReview(
            Authentication authentication,
            @PathVariable("restaurantId") long restaurantId, @Validated @RequestBody Review review) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();

        review = Review.builder()
                .name(claims.get("name", String.class))
                .score(review.getScore())
                .restaurantId(restaurantId)
                .description(review.getDescription())
                .build();

        reviewService.addReview(review);

        URI location = new URI("/restaurants/" + restaurantId +"/reviews/" + review.getId());
        return ResponseEntity.created(location).body("{}");
    }
}
