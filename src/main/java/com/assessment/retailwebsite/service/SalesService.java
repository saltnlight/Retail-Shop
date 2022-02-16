package com.assessment.retailwebsite.service;

import com.assessment.retailwebsite.payload.request.SalesOrder;
import com.assessment.retailwebsite.payload.response.ProcessedPayment;

public interface SalesService {

    ProcessedPayment processSales(SalesOrder salesOrder);
}
