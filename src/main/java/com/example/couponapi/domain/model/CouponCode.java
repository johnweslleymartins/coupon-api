package com.example.couponapi.domain.model;

import com.example.couponapi.domain.exception.InvalidCouponCodeException;

public final class CouponCode {

    private static final int CODE_LENGTH = 6;

    private final String value;

    private CouponCode(String value) {
        this.value = value;
    }

    public static CouponCode of(String rawCode) {

        if (rawCode == null || rawCode.isBlank()) {
            throw new InvalidCouponCodeException();
        }

        String normalized = rawCode.replaceAll("[^A-Za-z0-9]", "");

        if (normalized.length() != CODE_LENGTH) {
            throw new InvalidCouponCodeException(rawCode);
        }

        return new CouponCode(normalized);
    }

    public String getValue() {
        return value;
    }
}
