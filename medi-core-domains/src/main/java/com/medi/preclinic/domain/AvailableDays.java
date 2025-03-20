package com.medi.preclinic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "AvailableDays")
@Data
public class AvailableDays implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String day;
	
	@Column(nullable = false)
	private String createdBy;
	
	@Column(nullable = false)
	private String lastModifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime")
	private Date createTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastModifiedTime")
	private Date lastModifiedTime;
	
}
