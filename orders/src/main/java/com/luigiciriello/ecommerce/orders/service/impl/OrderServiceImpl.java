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
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private OrderRepository orderRepository;
    private CatalogFeignClient catalogFeignClient;
    private CustomersFeignClient customersFeignClient;

    @Override
    public Long createOrder(final OrderRequestDto orderRequestDto) {
        final CommerceOrder order = OrderMapper.mapOrderDtoToEntity(orderRequestDto, new CommerceOrder());
        final Double totalPrice = getProducts(orderRequestDto.getProducts()).stream()
                .mapToDouble(productDto -> Objects.nonNull(productDto) ? productDto.getPrice() : 0).sum();

        final ResponseEntity<CustomerDto> customerResponse = customersFeignClient.fetchCustomer(order.getCustomerEmail());
        if (Objects.nonNull(customerResponse) && Objects.nonNull(customerResponse.getBody())) {
            order.setShippingAddress(customerResponse.getBody().getAddress());
        }

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
        final ResponseEntity<CustomerDto> customerDtoResponseEntity = customersFeignClient.fetchCustomer(commerceOrder.getCustomerEmail());

        if (Objects.nonNull(customerDtoResponseEntity) && Objects.nonNull(customerDtoResponseEntity.getBody())) {
            responseDto.setCustomer(customerDtoResponseEntity.getBody());
        }

        responseDto.setTotalPrice(commerceOrder.getTotalPrice());

        return responseDto;
    }

    private List<ProductDto> getProducts(final List<String> productCodes) {
        return productCodes.stream()
                .map(catalogFeignClient::fetchProduct)
                .filter(Objects::nonNull)
                .filter(ResponseEntity::hasBody)
                .filter(r -> r.getStatusCode().is2xxSuccessful())
                .map(ResponseEntity::getBody)
                .toList();
    }
}
