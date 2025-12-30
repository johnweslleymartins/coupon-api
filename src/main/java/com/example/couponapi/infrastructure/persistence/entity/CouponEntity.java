package com.example.couponapi.infrastructure.persistence.entity;

import com.example.couponapi.domain.model.Coupon;
import com.example.couponapi.domain.model.CouponStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "coupon")
public class CouponEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, unique = true, length = 20)
    private String code;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(name = "discount_value", nullable = false)
    private BigDecimal discountValue;

    @Column(name = "expiration_date", nullable = false)
    private OffsetDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CouponStatus status;

    @Column(nullable = false)
    private boolean published;

    @Column(nullable = false)
    private boolean redeemed;

    protected CouponEntity() {}

    private CouponEntity(
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

    public static CouponEntity from(Coupon coupon) {
        return new CouponEntity(
                coupon.getId(),
                coupon.getCode(),
                coupon.getDescription(),
                coupon.getDiscountValue(),
                coupon.getExpirationDate(),
                CouponStatus.ACTIVE,
                coupon.isPublished(),
                false
        );
    }

    public void markAsDeleted() {
        this.status = CouponStatus.DELETED;
    }

    public UUID getId() { return id; }
    public String getCode() { return code; }
    public String getDescription() { return description; }
    public BigDecimal getDiscountValue() { return discountValue; }
    public OffsetDateTime getExpirationDate() { return expirationDate; }
    public CouponStatus getStatus() { return status; }
    public boolean isPublished() { return published; }
    public boolean isRedeemed() { return redeemed; }

}
