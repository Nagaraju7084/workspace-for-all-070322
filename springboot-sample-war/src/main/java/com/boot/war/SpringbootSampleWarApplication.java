package com.boot.war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootSampleWarApplication {
	
	@GetMapping
	public String beanStalk() {
		return "beanstalk deployment";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSampleWarApplication.class, args);
	}

}
