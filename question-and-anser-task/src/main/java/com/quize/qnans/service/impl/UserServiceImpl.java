package com.quize.qnans.service.impl;

import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quize.qnans.dto.UserDto;
import com.quize.qnans.entities.User;
import com.quize.qnans.entities.UserRole;
import com.quize.qnans.exception.ResourceNotFoundException;
import com.quize.qnans.exception.UserFoundException;
import com.quize.qnans.repositories.RoleRepository;
import com.quize.qnans.repositories.UserRepository;
import com.quize.qnans.service.UserService;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //creating user
    @Override
    public UserDto createUser(UserDto userDto, Set<UserRole> userRoles) throws Exception {
    	
    	User user = dtoToUser(userDto);

        User local = this.userRepository.findByUsername(user.getUsername());
        if (local != null) {
            System.out.println("User is already there !!");
            throw new UserFoundException();
        } else {
            //user create
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);

        }

        return userToDto(local);
    }

    //getting user by username
    @Override
    public UserDto getUser(String username) {
        return userToDto(userRepository.findByUsername(username));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
        .orElseThrow(
        		()->new ResourceNotFoundException("User", "user id", userId)
        		);
        userRepository.deleteById(userId);
    }
    
    public UserDto userToDto(User user) {
    	UserDto userDto = new UserDto();
    	BeanUtils.copyProperties(user, userDto);
    	return userDto;
    }
    
    public User dtoToUser(UserDto userDto) {
    	User user = new User();
    	BeanUtils.copyProperties(userDto, user);
    	return user;
    }


}
