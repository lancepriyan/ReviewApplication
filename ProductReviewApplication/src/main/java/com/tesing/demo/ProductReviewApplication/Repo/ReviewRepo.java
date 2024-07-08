package com.tesing.demo.ProductReviewApplication.Repo;

import com.tesing.demo.ProductReviewApplication.Model.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ReviewRepo {

    List<Review> dataOfReviews = new ArrayList<>(Arrays.asList(
            new Review(1, 101, 201, 4, "Pricing", "Good Pricing.!"),
            new Review(2, 102, 202, 3, "Quality", "Good Quality.!"),
            new Review(3, 103, 203, 2, "Usage", "Love this Product.!"),
            new Review(4, 101, 204, 2, "Pricing", "Pricing wise Looks Good.!")
    ));

    public List<Review> getAllReviews(int productID) {
        return  dataOfReviews.stream().filter(review -> review.getProductID()==productID).toList();
    }

    public Review getReviewById(int id) {
        return dataOfReviews.stream().filter(review -> review.getId()==id).findFirst().orElse(null);
    }

    public void createReview(Review review) {
        dataOfReviews.add(review);
    }

    public Review getReviewByUserID(int userID) {
        return dataOfReviews.stream().filter(review -> review.getUserID()==userID).findFirst().orElse(null);
    }
}
