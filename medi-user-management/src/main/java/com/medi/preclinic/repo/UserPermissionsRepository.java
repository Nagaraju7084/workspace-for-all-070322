package com.medi.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.domain.MedilabUser;
import com.medi.preclinic.domain.UserPermission;

public interface UserPermissionsRepository extends JpaRepository<UserPermission, Integer> {

}
