package com.example.couponapi.api.dto.response;

import com.example.couponapi.infrastructure.persistence.entity.CouponEntity;
import com.example.couponapi.domain.model.CouponStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record CouponResponse(
        UUID id,
        String code,
        String description,
        BigDecimal discountValue,
        OffsetDateTime expirationDate,
        CouponStatus status,
        boolean published,
        boolean redeemed
) {
    public static CouponResponse from(CouponEntity couponEntity) {
        return new CouponResponse(
                couponEntity.getId(),
                couponEntity.getCode(),
                couponEntity.getDescription(),
                couponEntity.getDiscountValue(),
                couponEntity.getExpirationDate(),
                couponEntity.getStatus(),
                couponEntity.isPublished(),
                couponEntity.isRedeemed()
        );
    }
}
