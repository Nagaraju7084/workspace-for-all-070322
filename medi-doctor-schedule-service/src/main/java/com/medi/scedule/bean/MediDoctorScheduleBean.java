/**
 * 
 */
package com.medi.scedule.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @author SRI LALITHA DEVI
 *
 */
@Data
public class MediDoctorScheduleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long scheduleId;
	
	@NotEmpty(message = "doctor name must be selected. it should be have some value")
	private String doctorName;
	
	@Valid
	private List<DoctorsAvailableDaysBean> availableDays;
	
	/*
	 * dd/mm/yyyy hh:mm
	*/
	@NotEmpty(message = "start time must be provided. it should be have some value")
	private String startTime;
	
	/*
	 * dd/mm/yyyy hh:mm
	*/
	@NotEmpty(message = "start time must be provided. it should be have some value")
	private String endTime;
	
	private String message;
	
	private boolean scheduleStatus = true;//by default it is active(default status is decided by ui screen) 
	

}
