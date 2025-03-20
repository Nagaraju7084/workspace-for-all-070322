package com.quize.qnans.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class UserDto{

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean enabled = true;
    private String profile;

    private Set<UserRole> userRoles = new HashSet<>();

}
