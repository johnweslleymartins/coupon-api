package com.example.couponapi.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    /*@ExceptionHandler(InvalidCouponCodeException.class)
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
    }*/

}
