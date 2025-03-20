package com.mongodb.external.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Document
public class Employee {
	
	@Id
	private String id;
	
	@NonNull
	private Integer empId;
	
	@NonNull
	private String empName;
	
	@NonNull
	private double empSal;
}
