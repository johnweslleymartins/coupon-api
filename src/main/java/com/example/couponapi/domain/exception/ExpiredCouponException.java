package com.example.couponapi.domain.exception;

public class ExpiredCouponException extends RuntimeException {

    public ExpiredCouponException() {
        super("Cupom com data de expiração no passado");
    }

}
