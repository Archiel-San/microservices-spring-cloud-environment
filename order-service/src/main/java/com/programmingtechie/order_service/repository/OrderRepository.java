package com.programmingtechie.order_service.repository;

import com.programmingtechie.order_service.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
