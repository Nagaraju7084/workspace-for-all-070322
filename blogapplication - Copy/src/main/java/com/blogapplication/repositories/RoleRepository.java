package com.blogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.entities.Role;

public interface RoleRepository  extends JpaRepository<Role, Integer>{

}
