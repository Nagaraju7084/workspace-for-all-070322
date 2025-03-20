package com.medi.preclinic.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "UserToDepartment")
@Data
public class UserToDepartment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userToDeptId;
	
	@OneToOne
	private MedilabUser user;
	
	@OneToOne
	private Department dept;
	
	
	
	

}
