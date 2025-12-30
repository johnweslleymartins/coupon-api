package com.example.couponapi.exception;

import com.example.couponapi.api.dto.response.ApiErrorResponse;
import com.example.couponapi.domain.exception.ExpiredCouponException;
import com.example.couponapi.domain.exception.InvalidCouponCodeException;
import com.example.couponapi.domain.exception.InvalidDiscountValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(InvalidCouponCodeException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidCouponCode(InvalidCouponCodeException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(InvalidDiscountValueException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidDiscount(InvalidDiscountValueException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(ExpiredCouponException.class)
    public ResponseEntity<ApiErrorResponse> handleExpiredCoupon(ExpiredCouponException ex) {
        return ResponseEntity
                .unprocessableEntity()
                .body(ApiErrorResponse.of(ex.getMessage()));
    }

}
