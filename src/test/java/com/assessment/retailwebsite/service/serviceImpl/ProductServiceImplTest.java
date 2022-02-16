package com.assessment.retailwebsite.service.serviceImpl;

import com.assessment.retailwebsite.exception.AppEntityException;
import com.assessment.retailwebsite.model.Product;
import com.assessment.retailwebsite.payload.request.AddProductRequest;
import com.assessment.retailwebsite.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private AddProductRequest productReq;
    private Product product;

    @BeforeEach
    void setUp() {
        productReq = new AddProductRequest();
        productReq.setName("Carlo Rose");
        productReq.setCategory("Wine");
        productReq.setPrice(new BigDecimal(1500));
        productReq.setDescription("Lovely red wine");

        product = new Product();
        product.setName("Carlo Rose");
        product.setCategory("Wine");
        product.setPrice(new BigDecimal(1500));
        product.setDescription("Lovely red wine");
    }

    @Test
    void addProduct() {
        when(productRepository.findByName(productReq.getName())).thenReturn(Optional.empty());
        var res = productService.addProduct(productReq);
        assertNotNull(res);
        assertEquals("Product Saved", res.getMessage());
        assertEquals("Carlo Rose", res.getProduct().getName());
        assertEquals("Wine", res.getProduct().getCategory());
    }

    @Test
    void productExists() {
        when(productRepository.findByName(productReq.getName())).thenReturn(Optional.of(product));

        Exception exception = assertThrows(AppEntityException.class, () -> {
            productService.addProduct(productReq);
        });

        String expectedMessage = "This product already exists";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}