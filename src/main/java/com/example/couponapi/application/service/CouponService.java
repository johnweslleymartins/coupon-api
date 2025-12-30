package com.example.couponapi.application.service;

import com.example.couponapi.application.command.CreateCouponCommand;
import com.example.couponapi.domain.exception.CouponAlreadyDeletedException;
import com.example.couponapi.domain.exception.CouponNotFoundException;
import com.example.couponapi.domain.model.Coupon;
import com.example.couponapi.infrastructure.persistence.entity.CouponEntity;
import com.example.couponapi.domain.model.CouponStatus;
import com.example.couponapi.domain.gateway.CouponGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CouponService {

    private final CouponGateway gateway;

    public CouponService(CouponGateway gateway) {
        this.gateway = gateway;
    }

    public Coupon create(CreateCouponCommand command) {
        Coupon coupon = Coupon.create(
                command.code(),
                command.description(),
                command.discountValue(),
                command.expirationDate(),
                command.published()
        );

        CouponEntity entity = CouponEntity.from(coupon);
        gateway.save(entity);

        return coupon;
    }


    public Coupon getById(UUID id) {
        CouponEntity entity = gateway.findById(id)
                .orElseThrow(() -> new CouponNotFoundException(id));

        verifyCouponAlready(entity);

        return Coupon.restore(
                entity.getId(),
                entity.getCode(),
                entity.getDescription(),
                entity.getDiscountValue(),
                entity.getExpirationDate(),
                entity.getStatus(),
                entity.isPublished(),
                entity.isRedeemed()
        );
    }


    public void delete(UUID id) {
        CouponEntity entity = gateway.findById(id)
                .orElseThrow(() -> new CouponNotFoundException(id));

        verifyCouponAlready(entity);

        entity.markAsDeleted();

        gateway.save(entity);
    }

    private static void verifyCouponAlready(CouponEntity entity) {
        if (entity.getStatus() == CouponStatus.DELETED) {
            throw new CouponAlreadyDeletedException();
        }
    }

}
