package com.codingcomrades.fullstackbackend.repository;

import com.codingcomrades.fullstackbackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
