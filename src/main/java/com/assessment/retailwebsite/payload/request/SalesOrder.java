package com.assessment.retailwebsite.payload.request;

import lombok.Data;

import java.util.List;
@Data
public class SalesOrder {

    private Long userId;

    private List<ProductOrderRequest> productOrderList;
}
