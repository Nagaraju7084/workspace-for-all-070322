package com.medi.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medi.preclinic.model.MedilabUser;


public interface MedilabUserRepository extends JpaRepository<MedilabUser, Integer> {

	@Query(name = "findUserByEmail",value = "from MedilabUser user where user.email=:email")
	public MedilabUser findUserByEmail(@Param("email") String email);
}
