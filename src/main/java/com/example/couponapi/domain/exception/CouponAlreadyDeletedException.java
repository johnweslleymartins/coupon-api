package com.example.couponapi.domain.exception;

public class CouponAlreadyDeletedException extends RuntimeException {
    public CouponAlreadyDeletedException() {
        super("Cupom já deletado");
    }

}
