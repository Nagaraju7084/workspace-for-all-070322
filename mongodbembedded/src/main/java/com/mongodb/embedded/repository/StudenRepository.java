package com.mongodb.embedded.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.embedded.cellection.Student;

public interface StudenRepository extends MongoRepository<Student, String> {
	
	
}
