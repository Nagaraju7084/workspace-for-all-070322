package com.medi.preclinic;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.medi.preclinic.domain.UserPermission;
import com.medi.preclinic.domain.UserRole;
import com.medi.preclinic.domain.UserRoleToPermission;
import com.medi.preclinic.repo.PermissionRepo;
import com.medi.preclinic.repo.RoleRepo;

@SpringBootApplication
public class MediCoreDomainsApplication implements CommandLineRunner{

	@Autowired
	private RoleRepo manager;
	
	@Autowired
	private PermissionRepo permRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(MediCoreDomainsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		UserPermission permission = new UserPermission();
		permission.setName("view");
		permission.setId(100);
		
		permRepo.save(permission);
		
		/*
		 * UserRole role = new UserRole(); role.setName("admin");
		 * 
		 * UserRoleToPermission roleToPermission = new UserRoleToPermission();
		 * roleToPermission.setUserPermission(permission);
		 * roleToPermission.setUserRole(role);
		 * 
		 * Set<UserRoleToPermission> permissionsSet = new
		 * HashSet<UserRoleToPermission>(); permissionsSet.add(roleToPermission);
		 * 
		 * role.setPermissionsSet(permissionsSet);
		 * 
		 * manager.save(role);
		 */
		
		
	}

}
