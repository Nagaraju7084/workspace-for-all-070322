package com.mongodb.external.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.external.entity.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
