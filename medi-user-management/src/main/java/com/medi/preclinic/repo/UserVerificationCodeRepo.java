package com.medi.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medi.preclinic.domain.UserRole;
import com.medi.preclinic.domain.VerificationCode;

public interface UserVerificationCodeRepo extends JpaRepository<VerificationCode, Integer> {
	
	@Query("from VerificationCode vc where vc.code=:vcode")
	public VerificationCode findByVerificationCode(@Param("vcode") String vcode);
	
}
