package com.boot.dyna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.dyna.entity.Person;
import com.boot.dyna.repository.PersonRepository;

@SpringBootApplication
@RestController
public class SpringbootDynamoDbExampleApplication {

	@Autowired
	private PersonRepository personRepo;
	
	@PostMapping("/savePerson")
	public Person savePerson(@RequestBody Person person) {
		return personRepo.addPerson(person);
	}
	
	@GetMapping("/getPerson/{personId}")
	public Person findPerson(@PathVariable String personId) {
		return personRepo.findPersonByPersonId(personId);
	}
	
	@DeleteMapping("/deletePerson")
	public String deletePerson(@RequestBody Person person) {
		return personRepo.deletePerson(person);
	}
	
	@PutMapping("/updatePerson")
	public String updatePerson(@RequestBody Person person) {
		return personRepo.updatePerson(person);
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootDynamoDbExampleApplication.class, args);
	}

}
