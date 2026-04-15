package com.programmingtechie.order_service.controller;


import com.programmingtechie.order_service.dtos.OrderRequest;
import com.programmingtechie.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public ResponseEntity<List<?>> listOrders(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.listOrders());
        }
        catch(RuntimeException runtimeException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

//    @PostMapping("")
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
//    @TimeLimiter(name = "inventory")
//    @Retry(name = "inventory")
    //no response Entity ele qnd poe-se ? o circuit breaker n devolove erro correcto
//    public CompletableFuture<String> createOrder(@RequestBody OrderRequest orderRequest) {
//        return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));
//    }

    @PostMapping("")
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
//    @TimeLimiter(name = "inventory")
//    @Retry(name = "inventory")
    //no response Entity ele qnd poe-se ? o circuit breaker n devolove erro correcto
    public String createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }



    public CompletableFuture<String> fallbackMethod(
            OrderRequest orderRequest,
            RuntimeException runtimeException) {

        return CompletableFuture.supplyAsync(()-> "Oops! Something Went Wrong, Please Try Again After Some Time");
    }


}
