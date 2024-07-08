package com.tesing.demo.ProductReviewApplication.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Review {
    private int id;
    private int productID;
    private int userID;
    private int rating;
    private String title;
    private String Comment;
}
