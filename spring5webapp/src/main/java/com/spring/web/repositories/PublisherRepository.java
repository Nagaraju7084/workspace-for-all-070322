package com.spring.web.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.web.domain.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {

}
