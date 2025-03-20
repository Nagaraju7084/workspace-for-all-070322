package com.spring.web.repositories;


import org.springframework.data.repository.CrudRepository;

import com.spring.web.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
