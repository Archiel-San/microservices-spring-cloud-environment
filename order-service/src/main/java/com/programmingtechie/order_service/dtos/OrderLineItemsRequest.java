package com.programmingtechie.order_service.dtos;

import java.math.BigDecimal;


public record OrderLineItemsRequest(
        String skuCode, BigDecimal price, Integer quantity
) {
}
