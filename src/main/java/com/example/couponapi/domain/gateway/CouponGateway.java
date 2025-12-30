package com.example.couponapi.domain.gateway;

import com.example.couponapi.infrastructure.persistence.entity.CouponEntity;

import java.util.Optional;
import java.util.UUID;

public interface CouponGateway {

    void save(CouponEntity couponEntity);
    Optional<CouponEntity> findById(UUID id);

}


