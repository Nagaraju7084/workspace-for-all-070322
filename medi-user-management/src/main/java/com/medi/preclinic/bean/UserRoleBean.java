/**
 * 
 */
package com.medi.preclinic.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SRI LALITHA DEVI
 *
 */
@Data
@NoArgsConstructor
public class UserRoleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	
	private Set<UserPermissionBean> permissionsSet = new HashSet<UserPermissionBean>();

}
