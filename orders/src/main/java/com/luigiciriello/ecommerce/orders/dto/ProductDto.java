package com.luigiciriello.ecommerce.orders.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Product",
        description = "Schema to hold product information"
)
public class ProductDto {
    @Schema(description = "Product code", example = "P001")
    private String code;

    @Schema(description = "Product name", example = "T - Shirt")
    private String name;

    @Schema(description = "Product price", example = "599.0")
    private Double price;
}
