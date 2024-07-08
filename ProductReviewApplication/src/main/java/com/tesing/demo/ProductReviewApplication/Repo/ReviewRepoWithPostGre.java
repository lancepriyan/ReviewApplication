package com.tesing.demo.ProductReviewApplication.Repo;

import com.tesing.demo.ProductReviewApplication.Model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepoWithPostGre {


    @Autowired
    public JdbcTemplate jdbcTemplate;

    public List<Review> getAllReviews(int productID) {
//        return  dataOfReviews.stream().filter(review -> review.getProductID()==productID).toList();
    }

}
