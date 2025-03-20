package com.empcurd.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empcurd.backend.dto.EmployeeDto;
import com.empcurd.backend.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee")
	public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto){
		
		return new ResponseEntity<EmployeeDto>(employeeService.save(employeeDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/employee")
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto){
		return new ResponseEntity<EmployeeDto>(employeeService.save(employeeDto), HttpStatus.OK);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id){
		employeeService.delete(id);
		return ResponseEntity.ok("employee deleted successfully!");
	}
}
