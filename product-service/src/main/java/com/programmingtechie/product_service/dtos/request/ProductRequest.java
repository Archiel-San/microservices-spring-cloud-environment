package com.programmingtechie.product_service.dtos.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(
        String name,
        String description,
        BigDecimal price
) {
}
