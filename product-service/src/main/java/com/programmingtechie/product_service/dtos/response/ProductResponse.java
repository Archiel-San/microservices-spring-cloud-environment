package com.programmingtechie.product_service.dtos.response;

import com.programmingtechie.product_service.domain.Product;
import lombok.Builder;

import java.math.BigDecimal;


public record ProductResponse(
        String id,
        String name,
        String description,
        BigDecimal price

) {
    public ProductResponse(Product product){
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

}
