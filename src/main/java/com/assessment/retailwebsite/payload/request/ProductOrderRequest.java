package com.assessment.retailwebsite.payload.request;

import lombok.Data;

@Data
public class ProductOrderRequest {

    private Long productId;
    private int quantity;
}
