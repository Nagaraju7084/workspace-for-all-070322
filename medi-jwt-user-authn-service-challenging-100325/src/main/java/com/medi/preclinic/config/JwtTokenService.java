package com.medi.preclinic.config;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.medi.preclinic.util.ServiceUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenService {
	
	@Autowired
	private MedilabUserDetailsService userDetailsService;
	
	public String createToken(String loggedInUser, List<String> roles) {
		
		Claims identities = Jwts.claims();
		
		identities.setSubject(loggedInUser);
		identities.setIssuer(ServiceUtil.JWT_TOKEN_ISSUER);
		Date currentServerTime = new Date();
		identities.setIssuedAt(currentServerTime);
		identities.setExpiration(new Date(currentServerTime.getTime()+ServiceUtil.JWT_TOKEN_EXPIRY));
		
		identities.put("USER_ROLES", roles);
		
		return Jwts
				.builder()
				.setClaims(identities)
				.signWith(SignatureAlgorithm.HS256, ServiceUtil.JWT_TOKEN_API_KEY)
				.compact();
	}
	
	public boolean validateToken(String tokenWithBearer) {
		boolean isValidToken = false; //initially inserting false value to the boolean variable(isValidToken) because
		//default values are not assigned to local variables by jvm so we should assign value at the time of declaration
		String tokenWithoutBearer = resolveToken(tokenWithBearer);
		if(tokenWithoutBearer != null) {
			//get the user identities by using jwtsparser by passing the apikey in signingkey method
			//and tokenwithoutbearer in parseclaimsjws method
			Claims userIdentities = Jwts.parser().setSigningKey(ServiceUtil.JWT_TOKEN_API_KEY).parseClaimsJws(tokenWithoutBearer).getBody();
			//now get the userdetails by passing useridentities.getsubject()
			UserDetails userDetails = userDetailsService.loadUserByUsername(userIdentities.getSubject());
			if(userDetails != null) { //primary check => userDetails != null meaning user is already existed user
				//get the tokenexpirydate from useridentities and check token expiry with the current date(secondary check)
				//date in token is before the current date then valid(meaning token is withing the time period, will not expire)
				Date tokenExpiryDate = userIdentities.getExpiration();
				isValidToken = tokenExpiryDate.before(new Date());
			}
			//we may not expect the user from securitycontext because
			//this is not session based security but we are checking which user present in the context
			Authentication authn = SecurityContextHolder.getContext().getAuthentication();
			System.out.println(authn.getName());
		}
		return isValidToken;
	}
	
	public String resolveToken(String tokenWithBearer) {
		String tokenVal = null;
		if(tokenWithBearer.contains(ServiceUtil.JWT_TOKEN_TYPE)) {
			tokenVal = tokenWithBearer.replace(ServiceUtil.JWT_TOKEN_PREFIX, "");
		}
		return tokenVal;
	}
}
