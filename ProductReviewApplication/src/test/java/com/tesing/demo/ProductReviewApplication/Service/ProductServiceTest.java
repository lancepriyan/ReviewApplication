package com.tesing.demo.ProductReviewApplication.Service;

import com.tesing.demo.ProductReviewApplication.Model.Review;
import com.tesing.demo.ProductReviewApplication.Repo.ReviewRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private ReviewRepo reviewRepo;

    @InjectMocks
    private ProductService productService;

    AutoCloseable autoCloseable;
    Review review;
    Review review1;
    List<Review> listOfReview;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        review = new Review(1, 101, 201, 4, "Pricing", "Good Pricing.!");
        review1 = new Review(2, 101, 201, 2, "Pricing", "Good Pricing.!");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testGetAllReviews() {
        List<Review> listToMock = Collections.singletonList(review);
        when(reviewRepo.getAllReviews(101)).thenReturn(listToMock);
        assertThat(productService.getAllReviews(101).size()).isEqualTo(1);
        assertThat(productService.getAllReviews(101).get(0).getTitle()).isEqualTo("Pricing");
    }

    @Test
    void testGetSummaryOfReview() {
        List<Review> listToMock = Arrays.asList(review,review1);
        when(reviewRepo.getAllReviews(101)).thenReturn(listToMock);
        assertThat(productService.getSummaryOfReview(101).getAvgOfSummary()).isEqualTo(3);
    }

    @Test
    void testCreateReview() {
        assertThat(productService.createReview(review)).isEqualTo("Success");
    }

}