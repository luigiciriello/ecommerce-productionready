package com.luigiciriello.ecommerce.catalog.controller;

import com.luigiciriello.ecommerce.catalog.dto.ProductDto;
import com.luigiciriello.ecommerce.catalog.service.IProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/catalog", produces = MediaType.APPLICATION_JSON_VALUE)
public class CatalogController {
    private final IProductService productService;

    public CatalogController(final IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ProductDto getProduct(@RequestParam final String productCode) {
        return productService.getProductByCode(productCode);
    }

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        return productService.getProducts();

    }
}
