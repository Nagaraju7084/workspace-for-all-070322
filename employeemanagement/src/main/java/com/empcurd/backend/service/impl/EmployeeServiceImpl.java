package com.empcurd.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empcurd.backend.dto.EmployeeDto;
import com.empcurd.backend.entity.Employee;
import com.empcurd.backend.repository.EmployeeRepository;
import com.empcurd.backend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto save(EmployeeDto employeeDto) {
		return entityToDto(employeeRepository.save(dtoToEntity(employeeDto)));
	}

	@Override
	public EmployeeDto update(EmployeeDto employeeDto) {
		Employee dbEmployee = employeeRepository.findById(employeeDto.getEmpId()).get();
		dbEmployee.setEmpName(employeeDto.getEmpName());
		dbEmployee.setEmpEmail(employeeDto.getEmpEmail());
		employeeRepository.save(dbEmployee);
		return entityToDto(employeeRepository.save(dtoToEntity(employeeDto)));
	}

	@Override
	public void delete(int empId) {
		List<Employee> dbEmployee = employeeRepository.findAll();
		//find the employee names starts with r and collect those employee ids and email in map
		dbEmployee.stream().filter(e -> e.getEmpName().startsWith("r"))
		.collect(Collectors.toMap(Employee::getEmpId, Employee::getEmpName));
		//employeeRepository.delete(dbEmployee);
	}
	
	private EmployeeDto entityToDto(Employee employee) {
		EmployeeDto empDto = new EmployeeDto();
		BeanUtils.copyProperties(employee, empDto);
		return empDto;
	}
	
	private Employee dtoToEntity(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
		return employee;
	} 

}
