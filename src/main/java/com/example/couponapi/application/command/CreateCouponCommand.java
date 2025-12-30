package com.example.couponapi.application.command;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CreateCouponCommand(
        String code,
        String description,
        BigDecimal discountValue,
        OffsetDateTime expirationDate,
        boolean published
) {

    public CreateCouponCommand {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Coupon code must not be null or blank");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description must not be null or blank");
        }

        if (discountValue == null) {
            throw new IllegalArgumentException("Discount value must not be null");
        }

        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date must not be null");
        }
    }
}
