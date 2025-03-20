/**
 * 
 */
package com.medi.scedule.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.medi.scedule.bean.DaysEnum;

import lombok.Data;

/**
 * @author SRI LALITHA DEVI
 *
 */
@Entity
//@Table(name = "medi-doctors-schedule")
@Data
public class MediDoctorSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long scheduleId;
	
	@Column(nullable = false, unique = true, updatable = false)
	private String doctorName;
	
	@Column(nullable = false, unique = true)
	@OneToMany(targetEntity = DoctorsAvailableDays.class, cascade = CascadeType.ALL)
	private List<DoctorsAvailableDays> availableDays;
	
	/*
	 * dd/mm/yyyy hh:mm
	*/
	@Column(nullable = false, unique = true)
	private String startTime;
	
	/*
	 * dd/mm/yyyy hh:mm
	*/
	@Column(nullable = false, unique = true)
	private String endTime;
	
	private String message;
	/**
	 * data base stores its value as
	 * 0 : if the schedule status as false
	 * 1 : if the schedule status as true
	 */
	private boolean scheduleStatus = true;//by default it is active(default status is decided by ui screen) 
	

}
