package com.assessment.retailwebsite.service.serviceImpl;

import com.assessment.retailwebsite.exception.AppEntityException;
import com.assessment.retailwebsite.model.Product;
import com.assessment.retailwebsite.payload.request.AddProductRequest;
import com.assessment.retailwebsite.payload.response.ProductResponse;
import com.assessment.retailwebsite.repository.ProductRepository;
import com.assessment.retailwebsite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public ProductResponse addProduct(AddProductRequest addProductRequest) {
        Optional<Product> existingUser= productRepository.findByName(addProductRequest.getName());
        if(existingUser.isPresent()) throw new AppEntityException("This product already exists");
        Product product = new Product();
            product.setName(addProductRequest.getName());
            product.setDescription(addProductRequest.getDescription());
            product.setPrice(addProductRequest.getPrice());
            product.setCategory(addProductRequest.getCategory());
            productRepository.save(product);
            return new ProductResponse("Product Saved", product);
    }
}
