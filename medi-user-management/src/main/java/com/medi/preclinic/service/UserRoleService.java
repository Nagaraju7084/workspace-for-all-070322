/**
 * 
 */
package com.medi.preclinic.service;

import java.util.List;

import com.medi.preclinic.bean.UserRoleBean;
import com.medi.preclinic.domain.UserRole;

/**
 * @author SRI LALITHA DEVI
 *
 */
public interface UserRoleService {
	
	public UserRoleBean createRole(final UserRoleBean roleBean);
	public UserRoleBean findRoleById(final String roleId);
	public UserRoleBean updateRole(final UserRoleBean roleBean);
	public List<UserRoleBean> deleteRole(final UserRoleBean roleBean);
	public List<UserRoleBean> deleteRoleById(final String roleId);
	
	/*
		provisioning and deprovisioning operations
	*/
	
	public UserRoleBean provisioningUser(String userId, String roleId);
	public UserRoleBean deProvisioningUser(String userId, String roleId);
	
	public List<UserRoleBean> findAllRoles();
	
	public UserRole mapBeanToDomain(UserRoleBean userRoleBean);
	public UserRoleBean mapDomainToBean(UserRole userRole);

}
