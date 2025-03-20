package com.medilab.preclinic.config;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.medilab.preclinic.model.MedilabUser;
import com.medilab.preclinic.model.UserRole;
import com.medilab.preclinic.model.UserRoleToPermission;
import com.medilab.preclinic.repo.MediUserRepository;

@Component
public class MediUserPermissions implements PermissionEvaluator {
	
	@Autowired
	private MediUserRepository userRepository;

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		String loggedInUser = authentication.getName();
		System.out.println("loggedin user is:\t"+loggedInUser);
		
		String module = (String)targetDomainObject;
		System.out.println("module is:\t"+module);
		
		String authority = (String)permission;
		System.out.println("authority for loggedin user is:\t"+authority);
		
		MedilabUser dbUser = userRepository.findUserByEmail(loggedInUser);
		if(dbUser != null && null != dbUser.getRole()) {
			UserRole userRole = dbUser.getRole();
			String dbRole = userRole.getName();
			if(module.equalsIgnoreCase(dbRole)) {
				Set<UserRoleToPermission> permissionSet = userRole.getPermissionsSet();
				if(permissionSet != null && permissionSet.size() > 0) {
					for(UserRoleToPermission roleToPermission : permissionSet) {
						String dbPermission = roleToPermission.getUserPermission().getName();
						if(authority.equalsIgnoreCase(dbPermission)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		// TODO Auto-generated method stub
		return false;
	}

}
