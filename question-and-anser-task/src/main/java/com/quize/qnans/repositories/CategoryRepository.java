package com.quize.qnans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quize.qnans.entities.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
