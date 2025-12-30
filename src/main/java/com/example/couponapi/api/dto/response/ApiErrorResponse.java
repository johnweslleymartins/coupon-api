package com.example.couponapi.api.dto.response;

public record ApiErrorResponse(String message) {

    public static ApiErrorResponse of(String message) {
        return new ApiErrorResponse(message);
    }

}
