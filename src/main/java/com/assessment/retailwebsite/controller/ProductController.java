package com.assessment.retailwebsite.controller;

import com.assessment.retailwebsite.payload.request.AddProductRequest;
import com.assessment.retailwebsite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity addProduct(@RequestBody AddProductRequest addProductRequest){
        return ResponseEntity.ok(productService.addProduct(addProductRequest));
    }
}
