package com.boot.aws.rds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.aws.rds.entity.Book;
import com.boot.aws.rds.repository.BookRepository;

import lombok.SneakyThrows;

@SpringBootApplication
@RestController
public class SpringbootAwsRdsApplication {
	
	@Autowired
	private BookRepository bookRepo;
	
	@PostMapping
	public Book saveBook(@RequestBody Book book) {
		return bookRepo.save(book);
	}
	
	@GetMapping
	public List<Book> findBooks(){
		return bookRepo.findAll();
	}
	
	@SneakyThrows
	@GetMapping("/{id}")
	public Book findBook(@PathVariable int id) {
		return bookRepo.findById(id).get();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsRdsApplication.class, args);
	}

}
