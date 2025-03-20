package com.medi.preclinic.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "Appointment")
@Data
public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "patient")
	private MedilabUser patient;
	
	@OneToOne
	@JoinColumn(name = "dept")
	private MedilabUser dept;
	
	@OneToOne
	@JoinColumn(name = "doctor")
	private MedilabUser doctor;
	
	private String date;
	
	private String time;
	
	private String message;
	
	private String status;
	
	
}
