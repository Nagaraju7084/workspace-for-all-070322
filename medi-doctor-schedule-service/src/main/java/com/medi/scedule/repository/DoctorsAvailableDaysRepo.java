package com.medi.scedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.scedule.domain.DoctorsAvailableDays;

public interface DoctorsAvailableDaysRepo extends JpaRepository<DoctorsAvailableDays, Integer> {

}
