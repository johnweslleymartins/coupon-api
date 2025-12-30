package com.example.couponapi.domain.exception;

import java.math.BigDecimal;

public class InvalidDiscountValueException extends RuntimeException {

    public InvalidDiscountValueException(BigDecimal value) {
        super("Cupom com disconto inválido: " + value + ". Mínimo permitido é 0.5");
    }
}
