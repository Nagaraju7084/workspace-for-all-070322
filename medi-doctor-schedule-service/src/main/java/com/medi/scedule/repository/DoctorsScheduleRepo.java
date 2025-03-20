package com.medi.scedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.scedule.domain.MediDoctorSchedule;

public interface DoctorsScheduleRepo extends JpaRepository<MediDoctorSchedule, Long> {

}
