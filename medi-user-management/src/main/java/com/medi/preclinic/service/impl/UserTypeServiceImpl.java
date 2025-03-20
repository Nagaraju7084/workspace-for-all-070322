package com.medi.preclinic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medi.preclinic.bean.UserTypeBean;
import com.medi.preclinic.domain.MedilabUserType;
import com.medi.preclinic.repo.UserTypeRepository;
import com.medi.preclinic.service.UserTypeService;

@Service
public class UserTypeServiceImpl implements UserTypeService {
	
	@Autowired
	private UserTypeRepository userTypeRepo;

	@Override
	public UserTypeBean createUserType(UserTypeBean UserTypeBean) {
		return mapDomainToBean(userTypeRepo.save(mapBeanToDomain(UserTypeBean)));
	}

	@Override
	public UserTypeBean findUserTypeById(String UserTypeId) {
		Optional<MedilabUserType> userTypeOptional = userTypeRepo.findById(Integer.valueOf(UserTypeId));
		MedilabUserType userTypeDomain = userTypeOptional.get();
		return mapDomainToBean(userTypeDomain);
	}

	@Override
	public UserTypeBean updateUserType(UserTypeBean UserTypeBean) {
		return mapDomainToBean(userTypeRepo.save(mapBeanToDomain(UserTypeBean)));
	}

	@Override
	public List<UserTypeBean> deleteUserType(UserTypeBean UserTypeBean) {
		userTypeRepo.delete(mapBeanToDomain(UserTypeBean));
		return findAllUserTypes();
	}

	@Override
	public List<UserTypeBean> deleteUserTypeById(String UserTypeId) {
		userTypeRepo.deleteById(Integer.valueOf(UserTypeId));
		return findAllUserTypes();
	}

	@Override
	public List<UserTypeBean> findAllUserTypes() {
		List<UserTypeBean> userTypeBeanList = new ArrayList<>();
		List<MedilabUserType> userTypeList = userTypeRepo.findAll();
		if(userTypeList != null && userTypeList.size() > 0) {
			userTypeList.stream().forEach(userTypeDomain -> {
				userTypeBeanList.add(mapDomainToBean(userTypeDomain));
			});
		}
		return userTypeBeanList;
	}
	
	/*
	  converting bean to domain
	*/
	@Override
	public MedilabUserType mapBeanToDomain(UserTypeBean userTypeBean) {
		MedilabUserType userTypeDomain = new MedilabUserType();
		BeanUtils.copyProperties(userTypeBean, userTypeDomain);
		return userTypeDomain;
	}
	
	/*
	  converting domain to bean
	*/
	@Override
	public UserTypeBean mapDomainToBean(MedilabUserType userType) {
		UserTypeBean userTypeBean = new UserTypeBean();
		BeanUtils.copyProperties(userType, userTypeBean);
		return userTypeBean;
	}

}
