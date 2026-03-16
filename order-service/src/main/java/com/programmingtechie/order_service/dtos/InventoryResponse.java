package com.programmingtechie.order_service.dtos;


public record InventoryResponse(
        String skuCode,
        Boolean isInStock
) {

}


