package com.medi.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.domain.MedilabUserType;

public interface UserTypeRepository extends JpaRepository<MedilabUserType, Integer> {

}
