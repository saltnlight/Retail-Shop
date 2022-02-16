package com.assessment.retailwebsite.payload.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class AddProductRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private String category;
}
