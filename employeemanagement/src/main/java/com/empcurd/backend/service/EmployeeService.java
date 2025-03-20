package com.empcurd.backend.service;

import com.empcurd.backend.dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto save(EmployeeDto employeeDto);
	EmployeeDto update(EmployeeDto employeeDto);
	void delete(int empId);
}
