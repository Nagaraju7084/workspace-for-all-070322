package com.medi.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.domain.UserRole;

public interface RoleRepo extends JpaRepository<UserRole, Integer> {

}
