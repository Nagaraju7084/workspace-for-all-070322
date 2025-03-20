/**
 * 
 */
package com.medi.preclinic;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.medi.preclinic.bean.UserTypeBean;
import com.medi.preclinic.service.UserTypeService;

/**
 * @author SRI LALITHA DEVI
 *
 */
public class UserTypeServiceTest extends MediUserManagementApplicationTests {
	
	@Autowired
	private UserTypeService userTypeService;
	
	@Test
	public void testCreateUserType() {
		UserTypeBean userBean = new UserTypeBean();
		userBean.setUserType("doctor");
		userBean = userTypeService.createUserType(userBean);
		assertNotNull(userBean);
		assertNotNull(userBean.getId());
	}

}
