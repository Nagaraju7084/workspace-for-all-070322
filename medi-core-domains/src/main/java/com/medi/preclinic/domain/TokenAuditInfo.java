package com.medi.preclinic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TokenAuditInfo")
@Data
public class TokenAuditInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	/*
	 * audit information
	*/
	private String createdBy;
	private String createdDate;
	
	private String modifiedBy;
	private String modifiedDate;
	
	/**
	 * authnStore can be an IDP/db
	 */
	private String authnStore;
	private Date tokenRefreshedTime;
	private String olderAccessToken;
	private String newAccessToken;
	
	private String subject;
	
	@OneToOne
	private MedilabAuthentication authnToken;
	

}
