package com.luigiciriello.ecommerce.orders.service.client;

import com.luigiciriello.ecommerce.orders.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomersFallback implements CustomersFeignClient{
    @Override
    public ResponseEntity<CustomerDto> fetchCustomer(String email) {
        return null;
    }
}
