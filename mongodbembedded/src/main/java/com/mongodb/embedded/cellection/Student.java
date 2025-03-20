package com.mongodb.embedded.cellection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document // convert object as json format
public class Student {
	
	@Id
	private String id; //pk - value generated as hexa decimal number
					   //in string format on save(insert operation)
	
	@NonNull
	private Integer studentId;
	
	@NonNull
	private String studentName;
	
	@NonNull
	private double studentFee;

}
