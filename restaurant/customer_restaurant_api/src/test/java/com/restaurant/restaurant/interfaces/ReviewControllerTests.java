package com.restaurant.restaurant.interfaces;

import com.restaurant.restaurant.application.ReviewService;
import com.restaurant.restaurant.domain.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTests {

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void addReviewWithValidData() throws Exception {

        Review review = Review.builder()
                        .name("JOKER")
                        .score(3)
                        .description("Great!")
                        .build();

        reviewService.addReview(review);

        mvc.perform(post("/restaurants/1/reviews")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"JOKER\",\"score\":3,\"description\":\"Great!\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/1/reviews/" + review.getId()))
                .andExpect(content().string("{}"));

        verify(reviewService).addReview(review);
    }

    @Test
    public void addReviewWithInvalidData() throws Exception {
        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"score\":,\"description\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

}