package com.luigiciriello.ecommerce.catalog.service;

import com.luigiciriello.ecommerce.catalog.dto.ProductDto;

import java.util.List;

public interface IProductService {

    ProductDto getProductByCode(final String code);
    List<ProductDto> getProducts();
}
