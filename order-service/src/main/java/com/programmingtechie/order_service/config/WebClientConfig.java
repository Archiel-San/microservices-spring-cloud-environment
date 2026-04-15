package com.programmingtechie.order_service.config;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "webClientInventory")
    @LoadBalanced
    public WebClient.Builder webClientBuilder(ObservationRegistry observationRegistry) {
        return WebClient.builder()
                .observationRegistry(observationRegistry);
    }

    /*

    Your app                Eureka Server
   |                        |
           |  "I want to talk       |
           |   to inventory-service"|
            |─────────────────────── |
            |                        |
            |  "Here are 3 instances:|
            |   :8081, :8082, :8083" |
            |◄───────────────────────|
            |
            | LoadBalancer picks one (e.g. :8082)
   |
           ▼
    http://192.168.1.42:8082/api/products


     */

    /// Acredito que neste caso de haver varios invontory ligados, ele aponta para um apesarem de seerem os mesmos




}
