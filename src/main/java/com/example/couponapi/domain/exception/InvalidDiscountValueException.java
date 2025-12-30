package com.example.couponapi.domain.exception;

import java.math.BigDecimal;

public class InvalidDiscountValueException extends RuntimeException {

    private final BigDecimal discountValue;

    public InvalidDiscountValueException(BigDecimal value) {
        super("Cupom com desconto inválido: " + value + ". Mínimo permitido é 0.5");
        this.discountValue = value;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }
}

