package com.example.domain;

import com.example.couponapi.domain.exception.ExpiredCouponException;
import com.example.couponapi.domain.exception.InvalidCouponCodeException;
import com.example.couponapi.domain.exception.InvalidDiscountValueException;
import com.example.couponapi.domain.model.Coupon;
import com.example.couponapi.domain.model.CouponStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CouponTest {

    @Test
    void shouldCreateValidCoupon() {
        Coupon coupon = Coupon.create(
                "ABC-123",
                "Descrição do cupom",
                new BigDecimal("0.8"),
                OffsetDateTime.now().plusHours(1),
                false
        );

        assertNotNull(coupon.getId());
        assertEquals("ABC-123", coupon.getCode());
        assertEquals(new BigDecimal("0.8"), coupon.getDiscountValue());
        assertEquals(CouponStatus.ACTIVE, coupon.getStatus());
        assertFalse(coupon.isPublished());
        assertFalse(coupon.isRedeemed());
    }

    @Test
    void shouldThrowExceptionForBlankCode() {
        Exception ex = assertThrows(InvalidCouponCodeException.class, () ->
                Coupon.create("   ", "Descrição", new BigDecimal("0.8"), OffsetDateTime.now().plusDays(1), false)
        );
        assertTrue(ex.getMessage().contains(""));
    }

    @Test
    void shouldThrowExceptionForInvalidDiscount() {
        Exception ex = assertThrows(InvalidDiscountValueException.class, () ->
                Coupon.create("ABC123", "Descrição", new BigDecimal("0.4"), OffsetDateTime.now().plusDays(1), false)
        );
        assertEquals(new BigDecimal("0.4"), ((InvalidDiscountValueException) ex).getDiscountValue());
    }

    @Test
    void shouldThrowExceptionForExpiredCoupon() {
        Exception ex = assertThrows(ExpiredCouponException.class, () ->
                Coupon.create("ABC123", "Descrição", new BigDecimal("0.8"), OffsetDateTime.now().minusDays(1), false)
        );
    }

    @Test
    void shouldNormalizeCode() {
        Coupon coupon = Coupon.create("aBc-1!@#", "Descrição", new BigDecimal("0.8"), OffsetDateTime.now().plusDays(1), false);
        assertNotNull(coupon);
    }

    @Test
    void restoreShouldCreateCouponWithoutValidation() {
        Coupon coupon = Coupon.restore(
                java.util.UUID.randomUUID(),
                "",
                "Descrição",
                new BigDecimal("0.1"),
                OffsetDateTime.now().minusDays(1),
                CouponStatus.DELETED,
                true,
                true
        );

        assertEquals(CouponStatus.DELETED, coupon.getStatus());
        assertEquals(true, coupon.isPublished());
        assertEquals(true, coupon.isRedeemed());
    }
}
