package com.medi.preclinic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "VerificationCode")
@Data
public class VerificationCode implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String code;
	
	@Temporal(value = TemporalType.DATE)
	private Date codeGenDate;
	
	@Temporal(value = TemporalType.DATE)
	private Date codeActiveDate;
	
	/*
		confirmation code validity is 4320 minutes by default i.e. for 3 days
	*/
	private long codeValidity = 4320;
	private boolean codeVerified = false;
	
	@OneToOne(cascade = CascadeType.ALL)
	private MedilabUser user;

}
