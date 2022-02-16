package com.assessment.retailwebsite.service.serviceImpl;

import com.assessment.retailwebsite.exception.AppEntityException;
import com.assessment.retailwebsite.model.Product;
import com.assessment.retailwebsite.model.User;
import com.assessment.retailwebsite.payload.request.ProductOrderRequest;
import com.assessment.retailwebsite.payload.request.SalesOrder;
import com.assessment.retailwebsite.payload.response.ProcessedPayment;
import com.assessment.retailwebsite.repository.DiscountRepository;
import com.assessment.retailwebsite.repository.ProductRepository;
import com.assessment.retailwebsite.repository.UserRepository;
import com.assessment.retailwebsite.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final DiscountRepository discountRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Override
    public ProcessedPayment processSales(SalesOrder salesOrder) {
        BigDecimal productPrice = BigDecimal.ZERO;

        User user = userRepository.findById(salesOrder.getUserId()).orElseThrow(()->new AppEntityException("User not found. Kindly register"));
        int percentageDiscount = discountRepository.findByRole(user.getRole()).get().getPercentage();

        for (ProductOrderRequest request: salesOrder.getProductOrderList()) {
            Product product=productRepository.findById(request.getProductId())
                    .orElseThrow(()->new AppEntityException("Product with id: "+request.getProductId()+" does not exists"));
            BigDecimal individualProductPrice = product.getPrice().multiply(new BigDecimal(request.getQuantity()));

            if(!(product.getCategory().equalsIgnoreCase("groceries"))){
                BigDecimal discountedPrice = (individualProductPrice.multiply(new BigDecimal(percentageDiscount))).divide(BigDecimal.valueOf(100));
                individualProductPrice = individualProductPrice.subtract(discountedPrice);
            }
            productPrice = productPrice.add(individualProductPrice);
        }

        BigDecimal discountOnEvery100 = productPrice.divide(BigDecimal.valueOf(100));
        BigDecimal discountToBeRemovedOnEvery100 = discountOnEvery100.multiply(BigDecimal.valueOf(5));
        productPrice = productPrice.subtract(discountToBeRemovedOnEvery100);

        return new ProcessedPayment(productPrice);
    }
}
