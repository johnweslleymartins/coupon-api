package com.example.application;

import com.example.couponapi.application.command.CreateCouponCommand;
import com.example.couponapi.application.service.CouponService;
import com.example.couponapi.domain.exception.CouponAlreadyDeletedException;
import com.example.couponapi.domain.exception.CouponNotFoundException;
import com.example.couponapi.domain.model.Coupon;
import com.example.couponapi.domain.model.CouponStatus;
import com.example.couponapi.infrastructure.persistence.entity.CouponEntity;
import com.example.couponapi.domain.gateway.CouponGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CouponServiceTest {

    private CouponGateway gateway;
    private CouponService service;

    @BeforeEach
    void setup() {
        gateway = mock(CouponGateway.class);
        service = new CouponService(gateway);
    }

    @Test
    void shouldCreateCoupon() {
        CreateCouponCommand command = new CreateCouponCommand(
                "ABC123",
                "Teste cupom",
                new BigDecimal("0.8"),
                OffsetDateTime.now().plusDays(1),
                false
        );

        Coupon created = service.create(command);

        assertNotNull(created.getId());
        assertEquals("ABC123", created.getCode());
        assertEquals(new BigDecimal("0.8"), created.getDiscountValue());
        assertEquals(CouponStatus.ACTIVE, created.getStatus());

        ArgumentCaptor<CouponEntity> captor = ArgumentCaptor.forClass(CouponEntity.class);
        verify(gateway, times(1)).save(captor.capture());

        CouponEntity entitySaved = captor.getValue();
        assertEquals("ABC123", entitySaved.getCode());
        assertEquals(new BigDecimal("0.8"), entitySaved.getDiscountValue());
    }

    @Test
    void getByIdShouldThrowIfNotFound() {
        UUID id = UUID.randomUUID();
        when(gateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(CouponNotFoundException.class, () -> service.getById(id));
    }

    @Test
    void getByIdShouldThrowIfDeleted() {
        UUID id = UUID.randomUUID();
        Coupon coupon = Coupon.create(
                "ABC123",
                "Teste",
                new BigDecimal("0.8"),
                OffsetDateTime.now().plusDays(1),
                false
        );

        CouponEntity entity = CouponEntity.from(coupon);
        when(gateway.findById(id)).thenReturn(Optional.of(entity));

        assertThrows(CouponAlreadyDeletedException.class, () -> service.getById(id));
    }

    @Test
    void shouldDeleteCoupon() {
        UUID id = UUID.randomUUID();
        Coupon coupon = Coupon.create(
                "ABC123",
                "Teste",
                new BigDecimal("0.8"),
                OffsetDateTime.now().plusDays(1),
                false
        );

        CouponEntity entity = CouponEntity.from(coupon);
        when(gateway.findById(id)).thenReturn(Optional.of(entity));

        service.delete(id);

        assertEquals(CouponStatus.DELETED, entity.getStatus());
        verify(gateway, times(1)).save(entity);
    }

    @Test
    void deleteShouldThrowIfNotFound() {
        UUID id = UUID.randomUUID();
        when(gateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(CouponNotFoundException.class, () -> service.delete(id));
    }

}

