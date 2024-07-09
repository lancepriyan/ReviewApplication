package com.tesing.demo.ProductReviewApplication.Repo;

import com.tesing.demo.ProductReviewApplication.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepoWithJPA extends JpaRepository<Review,Integer> {

    Review findByUserID(int userID);

    List<Review> findByProductID(int productID);
}
