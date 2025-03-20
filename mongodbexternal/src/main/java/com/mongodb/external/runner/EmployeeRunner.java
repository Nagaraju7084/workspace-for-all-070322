package com.mongodb.external.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mongodb.external.entity.Employee;
import com.mongodb.external.repository.EmployeeRepository;

//@Component
public class EmployeeRunner implements CommandLineRunner {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		
		employeeRepository.deleteAll();
		
		employeeRepository.save(new Employee(10,"sam",1000.00));
		employeeRepository.save(new Employee(11,"aish",2000.00));
		employeeRepository.save(new Employee(12,"nayan",3000.00));
		
		employeeRepository.findAll().forEach(System.out::println);
	}

}
