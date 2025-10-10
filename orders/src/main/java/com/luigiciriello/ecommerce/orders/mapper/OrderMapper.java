package com.luigiciriello.ecommerce.orders.mapper;

import com.luigiciriello.ecommerce.orders.dto.OrderRequestDto;
import com.luigiciriello.ecommerce.orders.entity.CommerceOrder;

public class OrderMapper {
    public static CommerceOrder mapOrderDtoToEntity(final OrderRequestDto source, final CommerceOrder target)
    {
        target.setCustomerEmail(source.getCustomerEmail());
        target.setProducts(source.getProducts());
        return target;
    }
}
