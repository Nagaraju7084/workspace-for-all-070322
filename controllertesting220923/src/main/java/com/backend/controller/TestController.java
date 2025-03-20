package com.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

	@GetMapping("/getName/{name}")
	public String getName(@PathVariable String name) {
		return name;
	}
	
	@PostMapping("/addName/{name}")
	public String addName(@PathVariable String name) {
		return name+" name posting";
	}
	
}
