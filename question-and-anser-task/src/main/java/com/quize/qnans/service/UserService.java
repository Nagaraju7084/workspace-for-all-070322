package com.quize.qnans.service;

import java.util.Set;

import com.quize.qnans.dto.UserDto;
import com.quize.qnans.entities.User;
import com.quize.qnans.entities.UserRole;

public interface UserService {
	
	//creating user
	public UserDto createUser(UserDto userDto, Set<UserRole> userRoles) throws Exception;
	
	//get user by username
	public UserDto getUser(String userName);
	
	//delete user by id
	void deleteUser(Long userId);
}
