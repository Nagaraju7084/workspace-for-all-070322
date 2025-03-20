package com.medi.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.domain.UserPermission;

public interface PermissionRepo extends JpaRepository<UserPermission, Integer> {

}
