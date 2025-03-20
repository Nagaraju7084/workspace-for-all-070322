package com.boot.datevalidation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.datevalidation.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
