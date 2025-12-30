package com.example.couponapi.domain.model;

import com.example.couponapi.domain.exception.ExpiredCouponException;
import com.example.couponapi.domain.exception.InvalidCouponCodeException;
import com.example.couponapi.domain.exception.InvalidDiscountValueException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Coupon {

    private static final BigDecimal MIN_DISCOUNT = new BigDecimal("0.5");

    private final UUID id;
    private final String code;
    private final String description;
    private final BigDecimal discountValue;
    private final OffsetDateTime expirationDate;

    private CouponStatus status;
    private final boolean published;
    private final boolean redeemed;

    private Coupon(
            UUID id,
            String code,
            String description,
            BigDecimal discountValue,
            OffsetDateTime expirationDate,
            CouponStatus status,
            boolean published,
            boolean redeemed
    ) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
        this.status = status;
        this.published = published;
        this.redeemed = redeemed;
    }

    public static Coupon create(
            String code,
            String description,
            BigDecimal discountValue,
            OffsetDateTime expirationDate,
            boolean published
    ) {

        String normalizedCode = normalizeCode(code);

        validateNormalizedCode(normalizedCode);
        validateExpiration(expirationDate);
        validateDiscount(discountValue);

        return new Coupon(
                UUID.randomUUID(),
                code,
                description,
                discountValue,
                expirationDate,
                CouponStatus.ACTIVE,
                published,
                false
        );
    }

    private static String normalizeCode(String code) {
        String normalized = code.replaceAll("[^A-Za-z0-9]", "")
                .toUpperCase();

        if (normalized.length() > 20) {
            normalized = normalized.substring(0, 20);
        }

        return normalized;
    }

    public static Coupon restore(
            UUID id,
            String code,
            String description,
            BigDecimal discountValue,
            OffsetDateTime expirationDate,
            CouponStatus status,
            boolean published,
            boolean redeemed
    ) {
        return new Coupon(
                id,
                code,
                description,
                discountValue,
                expirationDate,
                status,
                published,
                redeemed
        );
    }

    private static void validateNormalizedCode(String normalizedCode) {
        if (normalizedCode.isBlank()) {
            throw new InvalidCouponCodeException(normalizedCode);
        }
    }

    private static void validateExpiration(OffsetDateTime expirationDate) {
        if (expirationDate.isBefore(OffsetDateTime.now())) {
            throw new ExpiredCouponException();
        }
    }

    private static void validateDiscount(BigDecimal discountValue) {
        if (discountValue.compareTo(MIN_DISCOUNT) < 0) {
            throw new InvalidDiscountValueException(discountValue);
        }
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public OffsetDateTime getExpirationDate() {
        return expirationDate;
    }

    public CouponStatus getStatus() {
        return status;
    }

    public boolean isPublished() {
        return published;
    }

    public boolean isRedeemed() {
        return redeemed;
    }

}
