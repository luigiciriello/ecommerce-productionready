package com.luigiciriello.ecommerce.orders.service;

import com.luigiciriello.ecommerce.orders.dto.OrderRequestDto;
import com.luigiciriello.ecommerce.orders.dto.OrderResponseDto;

public interface IOrderService {
    Long createOrder(final OrderRequestDto orderRequestDto);
    OrderResponseDto getOrder(final String orderId);
}
