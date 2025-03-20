package com.medi.preclinic.config;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

@Component
public class OutBoundCommunicator {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${medilab.authn.userinfo.endpoint}")
	private String userEndpoint;
	
	public String getUserInfo(String accessToken) {
		System.out.println("access token going to idp is:\t"+accessToken);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+accessToken);
		
		HttpEntity requestData = new HttpEntity(headers);
		ignoreCertificates();
		ResponseEntity<String> iamResponse = restTemplate.exchange(userEndpoint, HttpMethod.GET, requestData, String.class);
		if(iamResponse.getStatusCodeValue() == HttpStatus.OK.value()) {
			System.out.println(iamResponse.getBody());
			String respBody = iamResponse.getBody();
			JSONObject jsonBuilder = new JSONObject(respBody);
			return jsonBuilder.toString();
		}
		
		return null;
	}
	
	private void ignoreCertificates() {
	    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
	        @Override
	        public X509Certificate[] getAcceptedIssuers() {
	            return null;
	        }

	        @Override
	        public void checkClientTrusted(X509Certificate[] certs, String authType) {
	        }

	        @Override
	        public void checkServerTrusted(X509Certificate[] certs, String authType) {
	        }
	    } };

	    try {
	        SSLContext sc = SSLContext.getInstance("TLS");
	        sc.init(null, trustAllCerts, new SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	    } catch (Exception e) {
	     

	    }

	}
	
}
