package com.medi.scedule.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medi.scedule.bean.MediDoctorScheduleBean;
import com.medi.scedule.service.MediDoctorsScheduleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1.0")
@Slf4j
public class MediDoctorsScheduleResource {
	
	@Autowired
	private MediDoctorsScheduleService scheduleService;
	
	@PostMapping(path = "/doctors-schedule", name = "createDoctorSchedule")
	public ResponseEntity<MediDoctorScheduleBean> createDoctorSchedule(@Valid @RequestBody MediDoctorScheduleBean scheduleBean) {
		log.info("scheduled data coming to service is:\t" + scheduleBean.toString());
		// todo
		// step 1 : data validation will go
		// step 2 : service integration will go
		scheduleService.createDoctorSchedule(scheduleBean);
		
		// step 3 : exception handling will go
		// step 4 : response content generation will go
		return ResponseEntity.ok(scheduleBean);
	}

	/*
	 * update schedule is similar to create schedule\ but the data coming to service
	 * is already the created schedule data
	 */
	@PutMapping(path = "/doctors-schedule", name = "updateDoctorSchedule")
	public ResponseEntity<MediDoctorScheduleBean> updateDoctorSchedule(
			@RequestBody MediDoctorScheduleBean scheduleBean) {
		log.info("scheduled data coming to service is:\t" + scheduleBean.toString());
		// todo
		// step 1 : data validation will go
		// step 2 : service integration will go
		// step 3 : exception handling will go
		// step 4 : response content generation will go
		return ResponseEntity.ok(scheduleBean);
	}

	@GetMapping(path = "/doctors-schedule/{schedule-id}", name = "getDoctorSchedule")
	public ResponseEntity<MediDoctorScheduleBean> getDoctorSchedule(@PathVariable("schedule-id") String scheduleId) {
		log.info("scheduled Id coming to service is:\t" + scheduleId);
		// todo
		// step 1 : data validation will go
		// step 2 : service integration will go
		// step 3 : exception handling will go
		// step 4 : response content generation will go
		return ResponseEntity.ok(null);
	}

	@GetMapping(path = "/doctors-schedule/{doctor-id}", name = "getDoctorScheduleByName")
	public ResponseEntity<List<MediDoctorScheduleBean>> getDoctorScheduleByName(
			@PathVariable("doctor-id") Integer doctorId) {
		log.info("doctor Id coming to service is:\t" + doctorId);
		// todo
		// step 1 : data validation will go
		// step 2 : service integration will go
		// step 3 : exception handling will go
		// step 4 : response content generation will go
		return ResponseEntity.ok(null);
	}

	@GetMapping(path = "/doctors-schedule", name = "findAllDoctorSchedules")
	public ResponseEntity<List<MediDoctorScheduleBean>> findAllDoctorSchedules() {
		// todo
		// step 1 : data validation will go
		// step 2 : service integration will go
		// step 3 : exception handling will go
		// step 4 : response content generation will go
		return ResponseEntity.ok(null);
	}

	/*
	 * once after a schedule is deleted with the supplied schedule id
	 * then service providing the remaining schedules irrespective of the doctors
	 * 
	*/
	@DeleteMapping(path = "/doctors-schedule/{schedule-id}", name = "deleteDoctorSchedules")
	public ResponseEntity<MediDoctorScheduleBean> deleteDoctorSchedules(@PathVariable("schedule-id") Long scheduleId) {
		log.info("scheduled Id coming to service is:\t" + scheduleId);
		// todo
		// step 1 : data validation will go
		// step 2 : service integration will go
		// step 3 : exception handling will go
		// step 4 : response content generation will go
		return ResponseEntity.ok(null);
	}

}
