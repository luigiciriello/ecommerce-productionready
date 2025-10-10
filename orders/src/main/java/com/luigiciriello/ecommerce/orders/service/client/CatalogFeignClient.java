package com.luigiciriello.ecommerce.orders.service.client;

import com.luigiciriello.ecommerce.orders.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("catalog")
public interface CatalogFeignClient {

    @GetMapping(value = "/catalog/product",consumes = "application/json")
    ResponseEntity<ProductDto> fetchProduct(@RequestParam final String productCode);
}
