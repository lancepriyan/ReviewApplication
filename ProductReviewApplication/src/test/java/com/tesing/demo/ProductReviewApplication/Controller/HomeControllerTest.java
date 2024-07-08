package com.tesing.demo.ProductReviewApplication.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tesing.demo.ProductReviewApplication.Model.Review;
import com.tesing.demo.ProductReviewApplication.Model.Summary;
import com.tesing.demo.ProductReviewApplication.Service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    AutoCloseable autoCloseable;
    Review review;
    Review review1;
    Review review2;
    Review review3;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        review = new Review(1, 101, 201, 4, "Pricing", "Good Pricing.!");
        review1 = new Review(2, 102, 202, 3, "Quality", "Good Quality.!");
        review2 = new Review(3, 103, 203, 2, "Usage", "Love this Product.");
        review3 = new Review(4, 101, 204, 2, "Pricing", "Pricing wise Looks Good.!");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllReviews() throws Exception {
        List<Review> listToMock = Arrays.asList(review, review3);
        when(productService.getAllReviews(101)).thenReturn(listToMock);

        this.mockMvc.perform(get("/products/101/reviews"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getSummaryOfReview() throws Exception {
        Summary summary = new Summary(3);
        when(productService.getSummaryOfReview(101)).thenReturn(summary);

        this.mockMvc.perform(get("/products/101/reviews/summary"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void createReview() throws Exception {
        Review creatableREview = new Review(10, 110, 210, 3, "Pricing", "Good Pricing.!");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestReviewToCreateJSON = objectWriter.writeValueAsString(creatableREview);

        this.mockMvc.perform(post("/products/101/reviews").
                        contentType(MediaType.APPLICATION_JSON).content(requestReviewToCreateJSON))
                .andDo(print()).andExpect(status().isCreated());

    }
}