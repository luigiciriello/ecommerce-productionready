package com.luigiciriello.ecommerce.catalog.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/catalog", produces = MediaType.APPLICATION_JSON_VALUE)
public class CatalogController {

    @GetMapping("/product")
    public String getProduct(@RequestParam final String productCode) {

        return productCode + "helloworld";

    }

    @GetMapping("/products")
    public String getProducts() {

        return "helloworld";

    }
}
