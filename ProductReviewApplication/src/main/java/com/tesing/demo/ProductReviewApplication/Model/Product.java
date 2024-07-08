package com.tesing.demo.ProductReviewApplication.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Product {
    private int prodId;
    private String prodName;
    private String description;
}
