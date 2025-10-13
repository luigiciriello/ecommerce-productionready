package com.luigiciriello.ecommerce.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/ecommerce/catalog/**")
                        .filters(f -> f.rewritePath("ecommerce/catalog/(?<segment>.*)", "catalog/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("catalogCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport") /*TODO to implement*/)
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)))
                        .uri("lb://CATALOG"))
                .route(p -> p
                        .path("/ecommerce/orders/**")
                        .filters(f -> f.rewritePath("ecommerce/orders/(?<segment>.*)", "orders/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("orderCircuitBreaker")))
                        .uri("lb://ORDERS"))
                .route(p -> p
                        .path("/ecommerce/customers/**")
                        .filters(f -> f.rewritePath("ecommerce/customers/(?<segment>.*)", "customers/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("customerCircuitBreaker")))
                        .uri("lb://CUSTOMERS")).build();
    }
}
