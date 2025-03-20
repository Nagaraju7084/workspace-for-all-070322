package com.spring.web.repositories;


import org.springframework.data.repository.CrudRepository;

import com.spring.web.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
