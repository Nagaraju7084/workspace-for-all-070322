package com.spring.security.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUserController {
	
	@PostMapping("/register")
	public String register() {
		return "register";
	}
}
