package com.luigiciriello.ecommerce.orders.service.impl;

import com.luigiciriello.ecommerce.orders.dto.OrderDto;
import com.luigiciriello.ecommerce.orders.entity.CommerceOrder;
import com.luigiciriello.ecommerce.orders.exception.ResourceNotFoundException;
import com.luigiciriello.ecommerce.orders.mapper.OrderMapper;
import com.luigiciriello.ecommerce.orders.repository.OrderRepository;
import com.luigiciriello.ecommerce.orders.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private OrderRepository orderRepository;

    @Override
    public Long createOrder(final OrderDto orderDto) {
        final CommerceOrder order = OrderMapper.mapOrderDtoToEntity(orderDto, new CommerceOrder());
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public OrderDto getOrder(final String orderId) {
        return OrderMapper.mapOrderEntityToDto(
                orderRepository.findById(Long.valueOf(orderId)).orElseThrow(()
                        -> new ResourceNotFoundException("Order", "id", orderId)), new OrderDto());
    }
}
