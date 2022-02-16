package com.assessment.retailwebsite.payload.response;

import com.assessment.retailwebsite.model.Product;
import com.assessment.retailwebsite.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponse {
    private String message;
    private Product product;
}
