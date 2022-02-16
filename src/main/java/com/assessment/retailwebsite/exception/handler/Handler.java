package com.assessment.retailwebsite.exception.handler;

import com.assessment.retailwebsite.exception.AppEntityException;
import com.assessment.retailwebsite.payload.response.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(value = {AppEntityException.class})
    public ResponseEntity handleCustomException(AppEntityException ex){
        String errorMsgDesc = ex.getLocalizedMessage();
        if (errorMsgDesc == null) errorMsgDesc = "";
        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMsgDesc);
        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleGenericException(Exception ex){
        String errorMsgDesc = ex.getLocalizedMessage();
        if (errorMsgDesc == null) errorMsgDesc = "";
        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMsgDesc);
        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }


}
