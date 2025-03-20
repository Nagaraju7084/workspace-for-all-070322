package com.mongodb.external;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MongodbexternalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbexternalApplication.class, args);
	}

}
