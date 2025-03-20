/**
 * 
 */
package com.medi.preclinic.service;

import java.util.List;

import com.medi.preclinic.bean.UserTypeBean;
import com.medi.preclinic.domain.MedilabUserType;

/**
 * @author SRI LALITHA DEVI
 *
 */
public interface UserTypeService {
	
	public UserTypeBean createUserType(final UserTypeBean UserTypeBean);
	public UserTypeBean findUserTypeById(final String UserTypeId);
	public UserTypeBean updateUserType(final UserTypeBean UserTypeBean);
	public List<UserTypeBean> deleteUserType(final UserTypeBean UserTypeBean);
	public List<UserTypeBean> deleteUserTypeById(final String UserTypeId);
	
	public List<UserTypeBean> findAllUserTypes();
	
	public MedilabUserType mapBeanToDomain(UserTypeBean userTypeBean);
	public UserTypeBean mapDomainToBean(MedilabUserType userType);

}
