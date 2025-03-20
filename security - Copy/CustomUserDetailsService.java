package com.task.project.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.task.project.entity.Users;
import com.task.project.exception.UserNotFoundException;
import com.task.project.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = usersRepository.findByEmail(email).orElseThrow(
				()-> new UserNotFoundException(String.format("User with emial : %s is not found", email))
				);
		Set<String> roles = new HashSet<>();
		roles.add("ROLE_ADMIN");
		return new User(user.getEmail(), user.getPassword(), userAuthorities(roles));
	}
	
	private Collection<? extends GrantedAuthority> userAuthorities(Set<String> roles){
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
	}

}
