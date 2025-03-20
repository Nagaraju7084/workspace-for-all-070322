package com.mongodb.basicoperations.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.basicoperations.entity.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
