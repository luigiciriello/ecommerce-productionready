package com.luigiciriello.ecommerce.catalog.controller;

import com.luigiciriello.ecommerce.catalog.dto.ErrorResponseDto;
import com.luigiciriello.ecommerce.catalog.dto.ProductDto;
import com.luigiciriello.ecommerce.catalog.service.IProductService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "REST APIs to get catalog information",
        description = "REST APIs to get a specific product info, as well as all product's info"
)
@RestController
@RequestMapping(value = "/catalog", produces = MediaType.APPLICATION_JSON_VALUE)
public class CatalogController {
    private final IProductService productService;

    public CatalogController(final IProductService productService) {
        this.productService = productService;
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = ProductDto.class)
                    )
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
                    description = "Product code not found in catalog",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/product")
    public ResponseEntity<ProductDto> getProduct(@RequestParam final String productCode) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByCode(productCode));
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = ProductDto.class))
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
                    description = "Product catalog is empty",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }
}
