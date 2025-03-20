package com.medi.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.domain.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
