package com.medi.preclinic.resource;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medi.preclinic.bean.MediUserBean;
import com.medi.preclinic.service.MediUserAuthnService;

@RestController
@RequestMapping("/api")
@CrossOrigin(
				allowCredentials = "true",
				allowedHeaders = "*",
				methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.OPTIONS},
				origins = {"http://localhost:3000"},
				maxAge = 3600l
			)
public class MediUserAuthnResource {
	
	@Autowired
	private MediUserAuthnService iamAuthnService;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@PostMapping("/token")
	public String authenticateUser(@RequestBody MediUserBean userBean) {
		String userAuthnResp = iamAuthnService.authenticate(userBean.getUserName(), userBean.getPassword());
		JSONObject jsonBuilder = new JSONObject(userAuthnResp);
		String accessToken = jsonBuilder.getString("access_token");
		System.out.println("access token in authn service is:\t"+accessToken);
		String refreshToken = jsonBuilder.getString("refresh_token");
		System.out.println("refresh token in authn service is:\t"+refreshToken);
		
		HashOperations hashMap = redisTemplate.opsForHash();
		hashMap.put("access_token", "Bearer", accessToken);
		hashMap.put("access_token", "Bearer", refreshToken);
		
		return iamAuthnService.authenticate(userBean.getUserName(), userBean.getPassword());
	}
	
	@GetMapping("/token")
	public String getUserInfo(HttpServletRequest request) {
		String tokenWithBearer = request.getHeader("Authorization");
		if(tokenWithBearer != null && tokenWithBearer.startsWith("Bearer")) {
			String accessToken = tokenWithBearer.replace("Bearer ", "");
			System.out.println("access token is:\t"+accessToken);
			return iamAuthnService.viewUserProfile(accessToken);
		}
		return tokenWithBearer;	
		
	}

}
