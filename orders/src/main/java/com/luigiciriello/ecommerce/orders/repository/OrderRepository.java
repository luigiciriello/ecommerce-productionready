package com.luigiciriello.ecommerce.orders.repository;

import com.luigiciriello.ecommerce.orders.entity.CommerceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CommerceOrder, Long> {
}
