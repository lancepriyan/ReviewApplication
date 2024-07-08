package com.tesing.demo.ProductReviewApplication.Service;

import com.tesing.demo.ProductReviewApplication.Model.Review;
import com.tesing.demo.ProductReviewApplication.Model.Summary;
import com.tesing.demo.ProductReviewApplication.Repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ReviewRepo reviewRepo;

    public List<Review> getAllReviews(int productID) {
        return reviewRepo.getAllReviews(productID);
    }

    public Summary getSummaryOfReview(int productID) {
        List<Review> reviewListGivenByProductID = reviewRepo.getAllReviews(productID);
        int sumOfRating = 0;
        for (Review review : reviewListGivenByProductID) {
            sumOfRating += review.getRating();
        }
        int avgOfRating = sumOfRating / reviewListGivenByProductID.size();
        return new Summary(avgOfRating);
    }

    public String createReview(Review review) {
        reviewRepo.createReview(review);
        return  "Success";
 }

    public Review getReviewById(int id) {
        return reviewRepo.getReviewById(id);
    }

    public Review getReviewByUserId(int userID) {
        return reviewRepo.getReviewByUserID(userID);
    }
}
