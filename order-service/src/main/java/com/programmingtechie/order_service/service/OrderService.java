package com.programmingtechie.order_service.service;

import com.programmingtechie.order_service.domain.Order;
import com.programmingtechie.order_service.domain.OrderLineItems;
import com.programmingtechie.order_service.dtos.InventoryResponse;
import com.programmingtechie.order_service.dtos.OrderLineItemsRequest;
import com.programmingtechie.order_service.dtos.OrderRequest;
import com.programmingtechie.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Qualifier("webClientInventory")
    private WebClient.Builder webClientBuilder;


    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems =
                orderRequest.orderLineItemsRequests().stream().map(this::maptoDto).toList() ;

        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();

        /// Call Inventory Service to see if order is in stock
        //explicacao::, retrieve acredito que torna ele synchronous e se fosse asynchronous ele nao iria devolver nada
        // bodyToMono esta a especificar qual e o tipo de dado que esta a receber?
        InventoryResponse[] result = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStrock = Arrays.stream(result).allMatch(InventoryResponse::isInStock);

        if(allProductsInStrock)
            orderRepository.save(order);
        else throw new IllegalArgumentException("Product Is Not In Stock Try Again Later");

    }



    /// So ta criar a lista de objectos em um so
    public OrderLineItems maptoDto(OrderLineItemsRequest orderLineItemsRequest){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsRequest.price());
        orderLineItems.setQuantity(orderLineItemsRequest.quantity());
        orderLineItems.setSkuCode(orderLineItemsRequest.skuCode());
        return orderLineItems;
    }

}
