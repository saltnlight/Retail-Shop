package com.assessment.retailwebsite.payload.response;

import com.assessment.retailwebsite.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProcessedPayment {
    private BigDecimal totalPrice;
}
