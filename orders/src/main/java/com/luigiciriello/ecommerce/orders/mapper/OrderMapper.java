package com.luigiciriello.ecommerce.orders.mapper;

import com.luigiciriello.ecommerce.orders.dto.OrderDto;
import com.luigiciriello.ecommerce.orders.entity.CommerceOrder;

public class OrderMapper {

    public static OrderDto mapOrderEntityToDto(final CommerceOrder source, final OrderDto target)
    {
        target.setCustomerEmail(source.getCustomerEmail());
        target.setShippingAddress(source.getShippingAddress());
        target.setProducts(source.getProducts());
        target.setTotalPrice(source.getTotalPrice());
        return target;
    }

    public static CommerceOrder mapOrderDtoToEntity(final OrderDto source, final CommerceOrder target)
    {
        target.setCustomerEmail(source.getCustomerEmail());
        target.setShippingAddress(source.getShippingAddress());
        target.setProducts(source.getProducts());
        target.setTotalPrice(source.getTotalPrice());
        return target;
    }
}
