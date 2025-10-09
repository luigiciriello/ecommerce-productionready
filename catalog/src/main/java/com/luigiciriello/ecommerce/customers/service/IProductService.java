package com.luigiciriello.ecommerce.customers.service;

import com.luigiciriello.ecommerce.customers.dto.ProductDto;

import java.util.List;

public interface IProductService {

    ProductDto getProductByCode(final String code);
    List<ProductDto> getProducts();
}
