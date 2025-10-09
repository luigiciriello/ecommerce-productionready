package com.luigiciriello.ecommerce.orders.controller;

import com.luigiciriello.ecommerce.orders.dto.ErrorResponseDto;
import com.luigiciriello.ecommerce.orders.dto.OrderDto;
import com.luigiciriello.ecommerce.orders.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
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
        name = "REST APIs to place and get orders information"
)
@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersController {
    private final IOrderService orderService;

    public OrdersController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(
            summary = "Create Order REST API",
            description = "REST API to create new order"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/place")
    public ResponseEntity<Long> createOrder(@Valid @RequestBody final OrderDto orderDto) {
        final Long orderId = orderService.createOrder(orderDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderId);
    }

    @Operation(
            summary = "Get Order REST API",
            description = "REST API to get order info"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/get")
    public ResponseEntity<OrderDto> getOrder(@Valid @RequestParam final String orderId) {
        final OrderDto orderDto = orderService.getOrder(orderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderDto);
    }
}
