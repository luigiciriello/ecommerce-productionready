package com.luigiciriello.ecommerce.orders.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "Order",
        description = "Schema to hold order placement information"
)
public class OrderDto {
    @Schema(description = "Products in order")
    @NotEmpty(message = "Products cannot be empty")
    private List<String> products;

    @Schema(description = "Order Total Price")
    private Double totalPrice;

    @Schema(description = "Customer Email")
    @NotEmpty(message = "CustomerEmail cannot be empty")
    private String customerEmail;

    @Schema(description = "Shipping Address")
    @NotEmpty(message = "CustomerAddress cannot be empty")
    private String shippingAddress;
}
