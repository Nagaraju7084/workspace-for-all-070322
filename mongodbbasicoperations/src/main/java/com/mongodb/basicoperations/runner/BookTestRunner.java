package com.mongodb.basicoperations.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mongodb.basicoperations.entity.Book;
import com.mongodb.basicoperations.repository.BookRepository;

@Component
public class BookTestRunner implements CommandLineRunner {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public void run(String... args) throws Exception {
		bookRepository.insert(new Book("java",200.00));
		bookRepository.insert(new Book("c",150.00));
		bookRepository.insert(new Book("python",120.00));
		
		bookRepository.insert(Arrays.asList(new Book("java", 100.00), new Book("c", 75.00)));
		 
		
		System.out.println("done");
	}

}
