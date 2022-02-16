package com.assessment.retailwebsite.service;

import com.assessment.retailwebsite.payload.request.AddProductRequest;
import com.assessment.retailwebsite.payload.response.ProductResponse;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ProductResponse addProduct(AddProductRequest addProductRequest);
}
