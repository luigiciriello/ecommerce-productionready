package com.luigiciriello.ecommerce.orders.service;

import com.luigiciriello.ecommerce.orders.dto.OrderDto;

public interface IOrderService {
    Long createOrder(final OrderDto orderDto);
    OrderDto getOrder(final String orderId);
}
