package com.medi.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.model.UserPermission;


public interface UserPermissionsRepository extends JpaRepository<UserPermission, Integer> {

}
