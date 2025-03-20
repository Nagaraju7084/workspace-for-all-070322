package com.quize.qnans.dto;

import lombok.Data;

@Data
public class UserRole {

    private Long userRoleId;

    private UserDto user;

    private RoleDto role;

}
