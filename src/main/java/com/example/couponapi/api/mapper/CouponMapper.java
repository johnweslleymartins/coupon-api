package com.example.couponapi.api.mapper;

import com.example.couponapi.domain.model.Coupon;
import com.example.couponapi.api.dto.response.CouponResponse;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper {

    private CouponMapper() {}

    public static CouponResponse toResponse(Coupon couponEntity) {
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
