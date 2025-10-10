package com.luigiciriello.ecommerce.orders.service.impl;

import com.luigiciriello.ecommerce.orders.dto.CustomerDto;
import com.luigiciriello.ecommerce.orders.dto.OrderRequestDto;
import com.luigiciriello.ecommerce.orders.dto.OrderResponseDto;
import com.luigiciriello.ecommerce.orders.dto.ProductDto;
import com.luigiciriello.ecommerce.orders.entity.CommerceOrder;
import com.luigiciriello.ecommerce.orders.exception.ResourceNotFoundException;
import com.luigiciriello.ecommerce.orders.mapper.OrderMapper;
import com.luigiciriello.ecommerce.orders.repository.OrderRepository;
import com.luigiciriello.ecommerce.orders.service.IOrderService;
import com.luigiciriello.ecommerce.orders.service.client.CatalogFeignClient;
import com.luigiciriello.ecommerce.orders.service.client.CustomersFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private OrderRepository orderRepository;
    private CatalogFeignClient catalogFeignClient;
    private CustomersFeignClient customersFeignClient;

    @Override
    public Long createOrder(final OrderRequestDto orderRequestDto) {
        final CommerceOrder order = OrderMapper.mapOrderDtoToEntity(orderRequestDto, new CommerceOrder());
        final Double totalPrice = getProducts(orderRequestDto.getProducts()).stream().mapToDouble(ProductDto::getPrice).sum();
        final CustomerDto customer = customersFeignClient.fetchCustomer(order.getCustomerEmail()).getBody();
        order.setShippingAddress(customer.getAddress());
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public OrderResponseDto getOrder(final String orderId) {
        final CommerceOrder commerceOrder = orderRepository.findById(Long.valueOf(orderId)).orElseThrow(()
                -> new ResourceNotFoundException("Order", "id", orderId));

        final OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setOrderId(String.valueOf(commerceOrder.getId()));
        responseDto.setProducts(getProducts(commerceOrder.getProducts()));
        responseDto.setCustomer(customersFeignClient.fetchCustomer(commerceOrder.getCustomerEmail()).getBody());
        responseDto.setTotalPrice(commerceOrder.getTotalPrice());

        return responseDto;
    }

    private List<ProductDto> getProducts(final List<String> productCodes) {
        return productCodes.stream().map(productCode -> catalogFeignClient.fetchProduct(productCode).getBody()).toList();
    }
}
