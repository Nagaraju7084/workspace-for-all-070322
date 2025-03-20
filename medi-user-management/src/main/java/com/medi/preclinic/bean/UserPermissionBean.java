/**
 * 
 */
package com.medi.preclinic.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SRI LALITHA DEVI
 *
 */
@Data
@NoArgsConstructor
public class UserPermissionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	
	public UserPermissionBean(String name) {
		super();
		this.name = name;
	}


}
