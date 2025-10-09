package com.luigiciriello.ecommerce.customers.mapper;

import com.luigiciriello.ecommerce.customers.dto.ProductDto;
import com.luigiciriello.ecommerce.customers.entity.Product;

public class ProductMapper {

    public static ProductDto mapProductToDto(final Product source, final ProductDto target)
    {
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setPrice(source.getPrice());

        return target;
    }
}
