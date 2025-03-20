package com.medi.preclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.medi.preclinic.idpclient.OutBoundCommunicator;

public class MediUserAuthnTest extends MediUserAuthnServiceApplicationTests {
	
	@Autowired
	private OutBoundCommunicator outBoundCommunicator;
	
	String userName = null;
	String password = null;
	
	@BeforeEach
	public void init() {
		userName = "charles";
		password = "testuser";
	}
	
	@Test
	public void testIdpAuthn() {
		outBoundCommunicator.authenticateId(userName, password);
	}

}
