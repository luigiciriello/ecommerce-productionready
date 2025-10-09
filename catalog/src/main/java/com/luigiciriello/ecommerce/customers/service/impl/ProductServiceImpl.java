package com.luigiciriello.ecommerce.customers.service.impl;

import com.luigiciriello.ecommerce.customers.dto.ProductDto;
import com.luigiciriello.ecommerce.customers.entity.Product;
import com.luigiciriello.ecommerce.customers.exception.ResourceNotFoundException;
import com.luigiciriello.ecommerce.customers.mapper.ProductMapper;
import com.luigiciriello.ecommerce.customers.repository.ProductRepository;
import com.luigiciriello.ecommerce.customers.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {
    private ProductRepository productRepository;

    @Override
    public ProductDto getProductByCode(final String code) {
        final Product productByCode = productRepository.findByCode(code).orElseThrow(() -> new ResourceNotFoundException("Product", "code", code));
        return ProductMapper.mapProductToDto(productByCode, new ProductDto());
    }

    @Override
    public List<ProductDto> getProducts() {
        final List<Product> products = productRepository.findAll();

        return products.stream().map(product -> ProductMapper.mapProductToDto(product, new ProductDto())).toList();
    }
}
