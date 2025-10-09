package com.luigiciriello.ecommerce.customers.controller;

import com.luigiciriello.ecommerce.customers.dto.CustomerDto;
import com.luigiciriello.ecommerce.customers.dto.ErrorResponseDto;
import com.luigiciriello.ecommerce.customers.service.ICustomerService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "REST APIs to get customers information",
        description = "REST APIs to get a specific product info, as well as all product's info"
)
@RestController
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private final ICustomerService customerService;

    public CustomerController(final ICustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Customer not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )

    @GetMapping
    public ResponseEntity<CustomerDto> getCustomer(@RequestParam final String email) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerByEmail(email));
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status Created"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("register")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody final CustomerDto customerDto) {
        customerService.registerCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.OK).body("Customer registered successfully");
    }
}
