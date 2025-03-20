package com.mongodb.basicoperations.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Document
@NoArgsConstructor
@RequiredArgsConstructor
public class Book {
	
	@Id
	private String id;
	
	@NonNull
	private String bookCode;
	
	@NonNull
	private double bookCost;
}
