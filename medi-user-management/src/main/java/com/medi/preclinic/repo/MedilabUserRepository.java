package com.medi.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.domain.MedilabUser;

public interface MedilabUserRepository extends JpaRepository<MedilabUser, Integer> {

}
