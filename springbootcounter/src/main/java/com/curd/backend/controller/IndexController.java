package com.curd.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curd.backend.service.CounterService;

@RestController
@RequestMapping("/api/v1")
public class IndexController {

	@Autowired
	private CounterService counterService;
	
	@PostMapping("/increment")
	public ResponseEntity<?> incrementCounter() {
		return ResponseEntity.ok(counterService.incrementCounter());
	}
	
	@GetMapping("/getCounter")
	public ResponseEntity<?> getCounter() {
		return ResponseEntity.ok(counterService.getCounter());
	}
	
}
