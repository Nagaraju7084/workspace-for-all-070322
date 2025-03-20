package com.medi.preclinic.service;

public interface MediUserAuthnService {
	
	public String authenticate(String userName, String password);
	public String renewToken(String refreshToken, String accessTokenToBeRenewed);
	public String validateToken(String accessToken);
	public String viewUserProfile(String accessToken);

}
