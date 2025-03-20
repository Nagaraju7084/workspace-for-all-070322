package com.medi.preclinic;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

public class UserPermissionsTest extends MediUserManagementApplicationTests {
	
	@Autowired
	private UserPermissionsRepository permissionsRepo;
	
	@Test
	public void createPermission() {
		String name = "view";
		String createdBy = "admin";
		LocalDate createdDate = LocalDate.now();
		Date date = Date.from(createdDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		UserPermission permission = new UserPermission(name, createdBy, date, createdBy, date);
		
		permissionsRepo.save(permission);
		assertNotNull(permission);
		assertNotNull(permission.getId());
		
		List<UserPermission> permissionsList = permissionsRepo.findAll();
		System.out.println(permissionsList.size());
		assertNotNull(permissionsList);
		
	}
	
}
