package com.luigiciriello.ecommerce.catalog.mapper;

import com.luigiciriello.ecommerce.catalog.dto.ProductDto;
import com.luigiciriello.ecommerce.catalog.entity.Product;

public class ProductMapper {

    public static ProductDto mapProductToDto(final Product source, final ProductDto target)
    {
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setPrice(source.getPrice());

        return target;
    }
}
