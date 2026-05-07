package com.programmingtechie.order_service.dtos;

import java.util.List;

public record OrderRequest(
        List<OrderLineItemsRequest> orderLineItemsRequests
) {
}
