package com.boot.aws.rds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.aws.rds.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
