package com.quize.qnans;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.quize.qnans.entities.Role;
import com.quize.qnans.entities.User;
import com.quize.qnans.entities.UserRole;
import com.quize.qnans.service.UserService;

@SpringBootApplication
public class QuestionAndAnserTaskApplication {// implements CommandLineRunner{
	
	//@Autowired
	//private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(QuestionAndAnserTaskApplication.class, args);
	}

	/*
	 * @Override public void run(String... args) throws Exception {
	 * System.out.println("starting code");
	 * 
	 * User user1 = new User(); user1.setFirstName("test1");
	 * user1.setLastName("test1"); user1.setUserName("testuser1");
	 * user1.setPassword("test1231"); user1.setEmail("test1@yopmail.com");
	 * user1.setProfile("test1.png");
	 * 
	 * Role role = new Role(); role.setRoleId(71L); role.setRoleName("view");
	 * 
	 * Set<UserRole> userRoles = new HashSet<>(); UserRole userRole = new
	 * UserRole(); userRole.setRole(role); userRole.setUser(user1);
	 * 
	 * User dbUser = userService.createUser(user1, userRoles);
	 * System.out.println("saved user"+dbUser); }
	 */

}
