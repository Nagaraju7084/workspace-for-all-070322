package com.medi.preclinic.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


@Entity
@Table(name = "DoctorSchedule")
@Data
public class DoctorSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int shcdId;
	
	@OneToOne
	@JoinColumn(name = "doctorId")
	private MedilabUser doctor;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctorSchedule")
	//@JoinColumn(name = "daysAvailable")
	private Set<DoctorAvailability> doctorsAvailability;
	
	private String startTime;
	
	private String endTime;
	
	private String message;
	
	private String scheduledStatus;
	
	@Column(nullable = false)
	private String createdBy;
	
	@Column(nullable = false)
	private String lastModifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedTime;
	

}
