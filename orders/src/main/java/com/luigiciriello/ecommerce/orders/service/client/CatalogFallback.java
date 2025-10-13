package com.luigiciriello.ecommerce.orders.service.client;

import com.luigiciriello.ecommerce.orders.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CatalogFallback implements CatalogFeignClient{

    @Override
    public ResponseEntity<ProductDto> fetchProduct(String productCode) {
        return null;
    }
}
