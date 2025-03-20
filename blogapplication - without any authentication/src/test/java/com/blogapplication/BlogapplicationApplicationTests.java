package com.blogapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blogapplication.repositories.UserRepository;

@SpringBootTest
class BlogapplicationApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testUserRepo() {
		String className = userRepository.getClass().getName();
		String packageName = userRepository.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packageName);
	}

}
