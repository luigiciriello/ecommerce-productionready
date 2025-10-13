package com.luigiciriello.ecommerce.orders.service.client;

import com.luigiciriello.ecommerce.orders.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "customers", fallback = CustomersFallback.class)
public interface CustomersFeignClient {

    @GetMapping(value = "/customers/get",consumes = "application/json")
    ResponseEntity<CustomerDto> fetchCustomer(@RequestParam final String email);
}
