package com.programmingtechie.inventory_service.dto.response;

import lombok.Builder;

@Builder
public record InventoryResponse(
        String skuCode,
        Boolean isInStock

) {
}
