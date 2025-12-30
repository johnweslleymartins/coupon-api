package com.example.couponapi.domain.exception;

public class InvalidCouponCodeException extends RuntimeException {

    public InvalidCouponCodeException() {
        super("Cupom deve conter no máximo 6 caracteres");
    }

    public InvalidCouponCodeException(String code) {
        super("Cupom inválido: " + code + ". Cupom deve conter no máximo 6 caracteres");
    }

}
