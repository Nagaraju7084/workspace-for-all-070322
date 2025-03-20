/**
 * 
 */
package com.medi.preclinic.service;

import java.util.List;

import com.medi.preclinic.bean.MedilabUserBean;

/**
 * @author SRI LALITHA DEVI
 *
 */
public interface UserProfileService {
	
	public MedilabUserBean createUser(final MedilabUserBean userBean, String callbackUrl);
	public MedilabUserBean findUserById(final String userId);
	public MedilabUserBean updateUser(final MedilabUserBean userBean);
	public List<MedilabUserBean> deleteUser(final MedilabUserBean userBean);
	public List<MedilabUserBean> deleteUserById(final String userId);
	
	/*
		provisioning and deprovisioning operations
	*/
	
	public MedilabUserBean provisioningUser(String userId, String roleId);
	public MedilabUserBean deProvisioningUser(String userId, String roleId);
	
	public List<MedilabUserBean> findAllUsers();
	
	public boolean verifyUser(String verificationCode);

}
