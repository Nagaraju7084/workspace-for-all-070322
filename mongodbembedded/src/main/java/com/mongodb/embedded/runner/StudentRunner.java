package com.mongodb.embedded.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mongodb.embedded.cellection.Student;
import com.mongodb.embedded.repository.StudenRepository;

@Component
public class StudentRunner implements CommandLineRunner {

	@Autowired
	private StudenRepository studentRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Student savedStudent = studentRepository.save(new Student(70,"sripriya",30.25));
		studentRepository.save(new Student(84,"nagaraju",32.45));
		studentRepository.save(new Student(90,"rajkumar",33.75));
		
		System.out.println(savedStudent.getId());
		
		//------------------
		studentRepository.findAll().forEach(System.out::println);
	}

}
