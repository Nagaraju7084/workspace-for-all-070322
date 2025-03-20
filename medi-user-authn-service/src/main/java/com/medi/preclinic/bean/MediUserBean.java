package com.medi.preclinic.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class MediUserBean implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;

}
