package com.example.couponapi.infrastructure.gateway;

import com.example.couponapi.infrastructure.persistence.entity.CouponEntity;
import com.example.couponapi.domain.gateway.CouponGateway;
import com.example.couponapi.infrastructure.persistence.repository.CouponRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CouponGatewayImpl implements CouponGateway {

    private final CouponRepository repository;

    public CouponGatewayImpl(CouponRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(CouponEntity couponEntity) {
        repository.save(couponEntity);
    }

    @Override
    public Optional<CouponEntity> findById(UUID id) {
        return repository.findById(id);
    }

}
