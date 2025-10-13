package com.luigiciriello.ecommerce.orders.controller;

import com.luigiciriello.ecommerce.orders.dto.ErrorResponseDto;
import com.luigiciriello.ecommerce.orders.dto.OrderRequestDto;
import com.luigiciriello.ecommerce.orders.dto.OrderResponseDto;
import com.luigiciriello.ecommerce.orders.service.IOrderService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(OrdersController.class);
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
    public ResponseEntity<Long> createOrder(@Valid @RequestBody final OrderRequestDto orderRequestDto) {
        final Long orderId = orderService.createOrder(orderRequestDto);

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

    @Retry(name = "getOrder", fallbackMethod = "getOrderFallback")
    @GetMapping("/get")
    public ResponseEntity<OrderResponseDto> getOrder(@Valid @RequestParam final String orderId) {
        final OrderResponseDto responseDto = orderService.getOrder(orderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }

    public ResponseEntity<OrderResponseDto> getOrderFallback(final String orderId, final Throwable throwable) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new OrderResponseDto()); //as a fallback return an empty order
    }


}
