/**
 * 
 */
package com.medi.preclinic;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.medi.preclinic.bean.MedilabUserBean;
import com.medi.preclinic.bean.UserRoleBean;
import com.medi.preclinic.bean.UserTypeBean;
import com.medi.preclinic.service.UserProfileService;

/**
 * @author SRI LALITHA DEVI
 *
 */
public class UserProfileServiceTest extends MediUserManagementApplicationTests {
	
	@Autowired
	private UserProfileService userService;
	
	MedilabUserBean userBean = null;
	
	static String USER_FIRST_NAME = "Alex";
	static String USER_LAST_NAME = "bean";
	static String USER_EMAIL = "beanalex@gmail.com";
	static String USER_ID = "alexb";
	static String USER_GENDER = "male";
	static String USER_BIOGRAPHY = "founder of acegi security";
	static boolean USER_STATUS = false;
	
	static String USER_DOB = "09/10/1974";
	
	static String USER_ROLE = "";
	static String USER_TYPE = "";
	
	/*
		for provisioning and deprovisioning
	*/
	static String USER_ROLE_ID = "1";
	static String USER_ID_PRIMARY = "1";
	
	@BeforeEach
	public void init() {
		userBean = new MedilabUserBean();
		userBean.setFirstName(USER_FIRST_NAME);
		userBean.setLastName(USER_LAST_NAME);
		userBean.setEmail(USER_EMAIL);
		userBean.setUserId(USER_ID);
		userBean.setGender(USER_GENDER);
		userBean.setShortBiography(USER_BIOGRAPHY);
		userBean.setStatus(USER_STATUS);
		userBean.setDob(USER_DOB);
	}
	
	@Test
	@Disabled
	public void testCreateUserProfile() {
		userBean = userService.createUser(userBean,null);
		assertNotNull(userBean);
		assertNotNull(userBean.getId());
	}
	
	@Test
	@Disabled
	public void testDeprovisioning() {
		MedilabUserBean userBean = userService.deProvisioningUser(USER_ID_PRIMARY, USER_ROLE_ID);
		assertNotNull(userBean);
		assertNull(userBean.getRole());
		
	}
	
	@Test
	public void testProvisioning() {
		MedilabUserBean userBean = userService.provisioningUser(USER_ID_PRIMARY, USER_ROLE_ID);
		assertNotNull(userBean);
		assertNotNull(userBean.getRole());
	}

}
