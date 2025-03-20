package com.medi.preclinic.service.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medi.preclinic.bean.MedilabUserBean;
import com.medi.preclinic.bean.UserRoleBean;
import com.medi.preclinic.bean.UserTypeBean;
import com.medi.preclinic.domain.MedilabUser;
import com.medi.preclinic.domain.MedilabUserType;
import com.medi.preclinic.domain.UserRole;
import com.medi.preclinic.domain.VerificationCode;
import com.medi.preclinic.repo.MedilabUserRepository;
import com.medi.preclinic.repo.UserVerificationCodeRepo;
import com.medi.preclinic.service.UserProfileService;
import com.medi.preclinic.service.UserRoleService;
import com.medi.preclinic.service.UserTypeService;
import com.medi.preclinic.util.NotificationOutboundCommunicator;
import com.medi.preclinic.util.ServiceUtil;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	private MedilabUserRepository userRepo;
	
	@Autowired
	private UserRoleService roleService;
	
	@Autowired
	private UserTypeService userService;
	
	@Autowired
	private UserVerificationCodeRepo codeVerifyRepo;
	
	@Autowired
	private NotificationOutboundCommunicator notificationOutboundCommunicator;

	@Override
	public MedilabUserBean createUser(MedilabUserBean userBean, String callbackUrl) {
		MedilabUser userModel = userRepo.save(mapBeanToDomain(userBean));
		JSONObject confirmAccountJsonBody = new JSONObject();
		if(userModel != null && userModel.getId() > 0) {
			// generate a verification code here
			VerificationCode vcode = new VerificationCode();
			vcode.setCode(RandomStringUtils.randomAlphabetic(15));
			vcode.setCodeGenDate(new Date());
			vcode.setUser(userModel);
			codeVerifyRepo.save(vcode);
			//callbackUrl = callbackUrl+"/"+vcode.getCode();
			confirmAccountJsonBody.put("userVerifyCode", vcode.getCode());
		}
		
		// we have to call the notification-service to send an email
		
		confirmAccountJsonBody.put("from", "sripriyanagaraju7084@gmail.com");
		confirmAccountJsonBody.put("to", new String[] {userModel.getEmail()});
		confirmAccountJsonBody.put("emailTemplateType", "AccountConfirmation");
		confirmAccountJsonBody.put("callbackUrl", callbackUrl);
		notificationOutboundCommunicator.sendConfirmAccountEmail(confirmAccountJsonBody.toString());
		
		return mapDomainToBean(userModel);
	}

	@Override
	public MedilabUserBean findUserById(String userId) {
		return mapDomainToBean(userRepo.findById(Integer.valueOf(userId)).get());
	}

	@Override
	public MedilabUserBean updateUser(MedilabUserBean userBean) {
		return mapDomainToBean(userRepo.save(mapBeanToDomain(userBean)));
	}

	@Override
	public List<MedilabUserBean> deleteUser(MedilabUserBean userBean) {
		userRepo.delete(mapBeanToDomain(userBean));
		return findAllUsers();
	}

	@Override
	public List<MedilabUserBean> deleteUserById(String userId) {
		userRepo.deleteById(Integer.valueOf(userId));
		return findAllUsers();
	}

	@Override
	public MedilabUserBean provisioningUser(String userId, String roleId) {
		MedilabUser userDomain = userRepo.findById(Integer.valueOf(userId)).get();
		if(userDomain != null) {
			UserRole roleDomain = userDomain.getRole();
			//lets write some better way of checking the role equality later on time
			if(roleDomain != null && roleDomain.getId() != Integer.valueOf(roleId)) {
				UserRoleBean promotedRoleBean = roleService.findRoleById(roleId);
				userDomain.setRole(roleService.mapBeanToDomain(promotedRoleBean));
			}
		}
		return mapDomainToBean(userRepo.save(userDomain));
	}

	/*
		revoking a role to user is called deprovisioning
	*/
	@Override
	public MedilabUserBean deProvisioningUser(String userId, String roleId) {
		MedilabUser userDomain = userRepo.findById(Integer.valueOf(userId)).get();
		if(userDomain != null) {
			UserRole roleDomain = userDomain.getRole();
			//lets write some better way of checking the role equality later on time
			if(roleDomain != null && roleDomain.getId() == Integer.valueOf(roleId)) {
				userDomain.setRole(null);
			}
		}
		return mapDomainToBean(userRepo.save(userDomain));
	}

	@Override
	public List<MedilabUserBean> findAllUsers() {
		List<MedilabUserBean> userBeanList = new ArrayList<>();
		List<MedilabUser> userDomainList = userRepo.findAll();
		if(userDomainList != null && userDomainList.size() > 0) {
			userDomainList.stream().forEach(userDomain ->{
				userBeanList.add(mapDomainToBean(userDomain));
			});
		}
		return userBeanList;
	}
	
	private MedilabUser mapBeanToDomain(MedilabUserBean userBean) {
		MedilabUser userDomain = new MedilabUser();
		BeanUtils.copyProperties(userBean, userDomain);
		
		String inputDob = userBean.getDob();
		try {
			userDomain.setDob(ServiceUtil.convertStringToDate(inputDob));
		} catch (ParseException e) {
			//todo : convert to user defined exception later on time
			e.printStackTrace();
		}
		
		/*
			auto provisioning while creating the user
		*/
		UserRoleBean roleBean = userBean.getRole();
		if(roleBean != null) {
			UserRole roleDomain = roleService.mapBeanToDomain(roleBean);
			userDomain.setRole(roleDomain);
		}
		
		UserTypeBean userTypeBean = userBean.getUserType();
		if(userTypeBean != null) {
			MedilabUserType userType = userService.mapBeanToDomain(userTypeBean);
			userDomain.setUserType(userType);
		}
		
		
		return userDomain;
	}
	
	private MedilabUserBean mapDomainToBean(MedilabUser userDomain) {
		MedilabUserBean userBean = new MedilabUserBean();
		BeanUtils.copyProperties(userDomain, userBean);
		
		Date inputDob = userDomain.getDob();
		userBean.setDob(inputDob.toString());
		
		UserRole roleDomain = userDomain.getRole();
		if(roleDomain != null) {
			UserRoleBean roleBean = roleService.mapDomainToBean(roleDomain);
			userBean.setRole(roleBean);
		}
		
		MedilabUserType userTypeDomain = userDomain.getUserType();
		if(userTypeDomain != null) {
			UserTypeBean userTypeBean = userService.mapDomainToBean(userTypeDomain);
			userBean.setUserType(userTypeBean);
		}
		
		return userBean;
	}

	@Override
	public boolean verifyUser(String verificationCode) {
		boolean isCodeVerified = false;
		VerificationCode verificationDomain = codeVerifyRepo.findByVerificationCode(verificationCode);
		if(!verificationDomain.isCodeVerified()) {
			MedilabUser medilabUser = verificationDomain.getUser();
			if(medilabUser != null && medilabUser !=null) {
				medilabUser.setAccountDisabled(false);
				medilabUser.setAccountLocked(false);
				verificationDomain.setCodeVerified(true);
				verificationDomain.setCodeActiveDate(new Date());
				verificationDomain = codeVerifyRepo.save(verificationDomain);
				if(verificationDomain.isCodeVerified()) {
					isCodeVerified = true;
				}
			}
		}else {
			//user friendly exception message saying that
			//.isCodeVerified user is already verified
			isCodeVerified = true;
		}
		return isCodeVerified;
	}

}
