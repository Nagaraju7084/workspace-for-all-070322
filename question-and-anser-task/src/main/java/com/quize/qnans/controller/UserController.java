package com.quize.qnans.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quize.qnans.dto.UserDto;
import com.quize.qnans.entities.Role;
import com.quize.qnans.entities.User;
import com.quize.qnans.entities.UserRole;
import com.quize.qnans.exception.UserFoundException;
import com.quize.qnans.service.UserService;
import com.quize.qnans.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//creating user
	@PostMapping("/createUser")
	public UserDto createUser(@RequestBody UserDto userDto) throws Exception {
		
		userDto.setProfile("default.png");
        //encoding password with bcryptpasswordencoder

		userDto.setPassword(this.bCryptPasswordEncoder.encode(userDto.getPassword()));

        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(new UserServiceImpl().dtoToUser(userDto));
        userRole.setRole(role);

        roles.add(userRole);
        
        return userService.createUser(userDto, roles);
	}
	
	//get user by user name
	@GetMapping("/getUser/{userName}")
	public UserDto getUser(@PathVariable("userName") String userName) {
		return userService.getUser(userName);
	}
	
	//delete user by id
	@DeleteMapping("/deleteUser/{userId}")
	public void deleteUser(@PathVariable("userId") long userId) {
		userService.deleteUser(userId);
	}
	
	//update
	
	@ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }
}
