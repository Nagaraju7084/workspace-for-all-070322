/**
 * 
 */
package com.medi.preclinic.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medi.preclinic.bean.MedilabUserBean;
import com.medi.preclinic.service.UserProfileService;

/**
 * @author SRI LALITHA DEVI
 *
 */
@RestController
@RequestMapping("/api/v1/")
public class UserProfileResource {
	
	@Autowired
	private UserProfileService userServcie;
	
	//@RequestMapping(value = "/users", method = RequestMethod.GET)
	@GetMapping(value = "/users")
	public  List<MedilabUserBean> findAllUsers() {
		return userServcie.findAllUsers();
	}
	
	//@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	@GetMapping(value = "/users/{userId}")
	public  MedilabUserBean findByUserId(@PathVariable ("userId") String userId) {
		return userServcie.findUserById(userId);
	}
	
	//@RequestMapping(value = "/users", method = RequestMethod.POST)
	@PostMapping(value = "/users")
	public  MedilabUserBean createUser(@RequestBody MedilabUserBean user, HttpServletRequest request) {
		String callbackUrl = request.getRequestURL().toString();
		callbackUrl = callbackUrl+"/verifyUser";
		return userServcie.createUser(user, callbackUrl);
	}
	
	//@RequestMapping(value = "/users", method = RequestMethod.PUT)
	@PutMapping(value = "/users")
	public  MedilabUserBean updateUser(@RequestBody MedilabUserBean updateUser, HttpServletRequest request) {
		String callbackUrl = request.getRequestURL().toString();
		callbackUrl = callbackUrl+"/verifyUser";
		return userServcie.createUser(updateUser, callbackUrl);
	}
	
	//@RequestMapping(value = "/users",method = RequestMethod.DELETE)
	@DeleteMapping(value = "/users")
	public  List<MedilabUserBean> deleteUser(@RequestBody MedilabUserBean userBean){
		return userServcie.deleteUser(userBean);
	}
	
	//@RequestMapping(value = "/users/{userId}",method = RequestMethod.DELETE)
	@DeleteMapping(value = "/users/{userId}")
	public  List<MedilabUserBean> deleteUserByUserId(@PathVariable("userId") String userId){
		return userServcie.deleteUserById(userId);
	}
	
	@GetMapping(value = "/users/verifyUser/{code}")
	public  ResponseEntity<String> verifyUser(@PathVariable ("code") String code, HttpServletRequest request) {
		System.out.println(request.getServletPath()+"\t servlet path");
		System.out.println(request.getContextPath()+"\t context path");
		System.out.println(request.getRequestURL().toString()+"\t request url");
		System.out.println(request.getScheme()+"\t scheme is");
		System.out.println(request.getServerPort()+"\t server port");
		System.out.println(request.getServerName()+"\t server name");
		
		JSONObject userVerifiedMsg = new JSONObject();
		boolean isUserVerified = userServcie.verifyUser(code);
		userVerifiedMsg.put("isUserVerified", isUserVerified);
		return ResponseEntity.ok(userVerifiedMsg.toString());
		
	}

}
