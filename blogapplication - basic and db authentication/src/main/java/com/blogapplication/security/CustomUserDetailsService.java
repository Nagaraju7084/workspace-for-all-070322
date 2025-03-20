package com.blogapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogapplication.entities.User;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.repositories.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loading user from db by username
		//userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "email", Long.valueOf(username)));
		User user = userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "email : "+ username, 0));
		return user;
	}

}
