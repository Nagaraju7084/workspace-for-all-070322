package com.client.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.client.*","com.client.*"})
@SpringBootApplication
public class ConfigClientTesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientTesterApplication.class, args);
	}

}
