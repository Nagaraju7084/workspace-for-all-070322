package com.empcurd.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empcurd.backend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	//based on name
	Employee findByEmpName(String empName);
	
	//start with n
	//@Query("from")

}
