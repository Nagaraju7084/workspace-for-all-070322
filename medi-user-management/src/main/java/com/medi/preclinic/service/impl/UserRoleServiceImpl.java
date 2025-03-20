package com.medi.preclinic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medi.preclinic.bean.UserRoleBean;
import com.medi.preclinic.domain.UserRole;
import com.medi.preclinic.repo.UserRoleRepository;
import com.medi.preclinic.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepo;

	@Override
	public UserRoleBean createRole(UserRoleBean roleBean) {
		return mapDomainToBean(userRoleRepo.save(mapBeanToDomain(roleBean)));
	}

	@Override
	public UserRoleBean findRoleById(String roleId) {
		Optional<UserRole> userRoleOptional = userRoleRepo.findById(Integer.valueOf(roleId));
		UserRole userRoleDomain = userRoleOptional.get();
		return mapDomainToBean(userRoleDomain);
	}

	@Override
	public UserRoleBean updateRole(UserRoleBean roleBean) {
		return mapDomainToBean(userRoleRepo.save(mapBeanToDomain(roleBean)));
	}

	@Override
	public List<UserRoleBean> deleteRole(UserRoleBean roleBean) {
		userRoleRepo.delete(mapBeanToDomain(roleBean));
		return findAllRoles();
	}

	@Override
	public List<UserRoleBean> deleteRoleById(String roleId) {
		userRoleRepo.deleteById(Integer.valueOf(roleId));
		return findAllRoles();
	}

	@Override
	public UserRoleBean provisioningUser(String userId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleBean deProvisioningUser(String userId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRoleBean> findAllRoles() {
		List<UserRoleBean> userRoleBeanList = new ArrayList<>();
		List<UserRole> userRoleList = userRoleRepo.findAll();
		if(userRoleList != null && userRoleList.size() > 0) {
			userRoleList.stream().forEach(userRoleDomain ->{
				userRoleBeanList.add(mapDomainToBean(userRoleDomain));
			});
		}
		return userRoleBeanList;
	}
	
	/*
	  converting bean to domain
	*/
	@Override
	public UserRole mapBeanToDomain(UserRoleBean UserRoleBean) {
		UserRole roleDomain = new UserRole();
		BeanUtils.copyProperties(UserRoleBean, roleDomain);
		return roleDomain;
	}
	
	/*
	  converting domain to bean
	*/
	@Override
	public UserRoleBean mapDomainToBean(UserRole role) {
		UserRoleBean userRoleBean = new UserRoleBean();
		BeanUtils.copyProperties(role, userRoleBean);
		return userRoleBean;
	}

}
