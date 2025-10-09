package com.luigiciriello.ecommerce.customers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold customer information"
)
public class CustomerDto {
    @Schema(description = "Customer email", example = "test@example.it")
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(description = "Customer name", example = "Luigi")
    @Size(min = 3, max = 30, message = "The length of the customer name should be between 3 and 30")
    private String name;

    @Size(min = 3, max = 30, message = "The length of the customer surname should be between 3 and 30")
    @Schema(description = "Customer surname", example = "Ciriello")
    private String surname;

    @Schema(description = "Customer address", example = "Via Roma 21, Bologna (BO) 40036, Italia")
    @Size(min = 3, max = 60, message = "The length of the customer address should be between 3 and 60")
    private String address;

}
