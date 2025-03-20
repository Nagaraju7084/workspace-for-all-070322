package com.medi.preclinic.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medi.preclinic.bean.MedilabUserBean;
import com.medi.preclinic.config.JwtTokenService;
import com.medi.preclinic.service.UserProfileService;
import com.medi.preclinic.util.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
public class MedilabUserResource {
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private JwtTokenService tokenService;
	
	@PostMapping("/users")
	public MedilabUserBean createUser(@RequestBody MedilabUserBean userBean) {
		System.out.println("i am in medilab user resource");
		userBean = userProfileService.createUser(userBean);
		return userBean;
	}
	
	@GetMapping("/users/{userId}")
	public MedilabUserBean getUserDetails(@PathVariable("userId") String userId) {
		System.out.println("i am in getUserDetails method");
		MedilabUserBean userBean = userProfileService.findUserById(userId);
		return userBean;
	}
	
	@GetMapping("/users/authn")
	public String authenticate() {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserName = authenticatedUser.getName();
		
		Collection<? extends GrantedAuthority> rolesList = authenticatedUser.getAuthorities();
		List<String> authorities = new ArrayList<>();
		if(rolesList != null && rolesList.size() > 0) {
			for(GrantedAuthority ga : rolesList) {
				authorities.add(ga.getAuthority());
			}
		}
		String token = tokenService.createToken(loggedInUserName, authorities);
		JSONObject jsonBuilder = new JSONObject();
		jsonBuilder.put("access_token", token);
		jsonBuilder.put("type", ServiceUtil.JWT_TOKEN_TYPE);
		return jsonBuilder.toString();
	}
	@GetMapping("/users")
	public MedilabUserBean getUser() {
		return new MedilabUserBean();
	}

}
