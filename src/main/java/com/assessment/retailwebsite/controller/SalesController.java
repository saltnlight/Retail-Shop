package com.assessment.retailwebsite.controller;

import com.assessment.retailwebsite.payload.request.SalesOrder;
import com.assessment.retailwebsite.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sales")
public class SalesController {

    private final SalesService salesService;

    @PostMapping("")
    public ResponseEntity processSales(@RequestBody SalesOrder salesOrder){
        return new ResponseEntity(salesService.processSales(salesOrder), HttpStatus.OK);
    }
}
