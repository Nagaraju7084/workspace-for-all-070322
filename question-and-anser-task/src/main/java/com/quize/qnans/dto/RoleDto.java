package com.quize.qnans.dto;


import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class RoleDto {

    private Long roleId;
    private String roleName;


    private Set<UserRole> userRoles=new HashSet<>();

}
