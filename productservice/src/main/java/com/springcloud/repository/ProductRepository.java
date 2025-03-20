package com.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springcloud.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product getByName(String name);

}
