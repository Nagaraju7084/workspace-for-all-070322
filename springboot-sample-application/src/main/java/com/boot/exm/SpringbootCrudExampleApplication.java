package com.boot.exm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootCrudExampleApplication {
	
	@GetMapping("/")
	public String home() {
		return "hello world";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudExampleApplication.class, args);
	}

}
