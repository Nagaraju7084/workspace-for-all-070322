package com.medi.preclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medi.preclinic.idpclient.OutBoundCommunicator;

@Service
public class MediUserAuthnServiceImpl implements MediUserAuthnService {
	
	@Autowired
	private OutBoundCommunicator iamClient;

	@Override
	public String authenticate(String userName, String password) {
		return iamClient.authenticateId(userName, password);
	}

	@Override
	public String renewToken(String refreshToken, String accessTokenToBeRenewed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validateToken(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String viewUserProfile(String accessToken) {
		return iamClient.getUserInfo(accessToken);
	}

}
