package com.quize.qnans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quize.qnans.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
