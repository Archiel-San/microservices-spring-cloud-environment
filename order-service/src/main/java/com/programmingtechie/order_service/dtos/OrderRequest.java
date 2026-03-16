package com.programmingtechie.order_service.dtos;

import com.programmingtechie.order_service.domain.OrderLineItems;

import java.util.List;

public record OrderRequest(
        List<OrderLineItemsRequest> orderLineItemsRequests
) {
}
