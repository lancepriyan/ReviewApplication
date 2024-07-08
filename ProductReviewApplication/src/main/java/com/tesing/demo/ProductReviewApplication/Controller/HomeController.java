package com.tesing.demo.ProductReviewApplication.Controller;

import com.tesing.demo.ProductReviewApplication.Model.Review;
import com.tesing.demo.ProductReviewApplication.Model.Summary;
import com.tesing.demo.ProductReviewApplication.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productID}/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable("productID") int productID) {
        List<Review> allReviews = productService.getAllReviews(productID);
        return ResponseEntity.status(HttpStatus.OK).body(allReviews);
    }

    @GetMapping("/{productID}/reviews/summary")
    public ResponseEntity<Summary> getSummaryOfReview(@PathVariable("productID") int productID) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getSummaryOfReview(productID));
    }


    @PostMapping("/{productID}/reviews")
    public ResponseEntity<Object> createReview(@PathVariable("productID") int productID, @RequestBody Review review) {
        Review reviewBythisUSer = productService.getReviewByUserId(review.getUserID());
        if (reviewBythisUSer != null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This User already Reviewed..!");
        }
        if (review.getRating() > 5) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The rating is Not valid!");
        }
        productService.createReview(review);
        Review createdReview = productService.getReviewById(review.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }


}
