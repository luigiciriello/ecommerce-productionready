package com.luigiciriello.ecommerce.catalog.repository;

import com.luigiciriello.ecommerce.catalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(final String name);
}
