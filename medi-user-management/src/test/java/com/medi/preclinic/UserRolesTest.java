package com.medi.preclinic;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.medi.preclinic.domain.UserPermission;
import com.medi.preclinic.domain.UserRole;
import com.medi.preclinic.domain.UserRoleToPermission;
import com.medi.preclinic.repo.UserPermissionsRepository;
import com.medi.preclinic.repo.UserRoleRepository;

public class UserRolesTest extends MediUserManagementApplicationTests {
	
	@Autowired
	private UserPermissionsRepository permissionsRepo;
	
	@Autowired
	private UserRoleRepository roleRepo;
	
	@Test
	public void createRole() {
		
		List<UserPermission> permissionsList = permissionsRepo.findAll();
		System.out.println(permissionsList.size());
		assertNotNull(permissionsList);
		
		UserRole role = new UserRole();
		role.setName("admin");
		
		Set<UserRoleToPermission> permissionSet = new HashSet<UserRoleToPermission>();
		if(permissionsList != null && permissionsList.size() > 0 ) {
			permissionsList.stream().forEach(up->{
				UserRoleToPermission roleToPermission = new UserRoleToPermission();
				roleToPermission.setUserPermission(up);
				//roleToPermission.setUserRole(role);
				permissionSet.add(roleToPermission);
			});
		}
		
		role.setPermissionsSet(permissionSet);
		roleRepo.save(role);
		assertNotNull(role.getId());
		
	}

}
