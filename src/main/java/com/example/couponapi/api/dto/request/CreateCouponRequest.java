package com.example.couponapi.api.dto.request;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateCouponRequest(

        @Schema(example = "ABC-123")
        String code,

        @Schema(example = "Cupom de desconto")
        String description,

        @Schema(example = "0.8")
        BigDecimal discountValue,

        @Schema(example = "2025-11-04T17:14:45.180Z")
        OffsetDateTime expirationDate,

        @Schema(example = "false")
        boolean published
) {}
