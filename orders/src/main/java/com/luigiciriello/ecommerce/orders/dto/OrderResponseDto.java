package com.luigiciriello.ecommerce.orders.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "OrderResponse",
        description = "Schema to hold order information"
)
public class OrderResponseDto {
    private String orderId;
    private List<ProductDto> products;
    private CustomerDto customer;
    private Double totalPrice;
}
