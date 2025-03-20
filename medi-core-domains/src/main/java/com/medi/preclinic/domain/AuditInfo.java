package com.medi.preclinic.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuditInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * audit information
	*/
	private String createdBy;
	private String createdDate;
	
	private String modifiedBy;
	private String modifiedDate;

}
