package com.backend.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.mysql.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
