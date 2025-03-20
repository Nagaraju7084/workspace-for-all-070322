package com.client.referral;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/api/referral")
public class ReferralController {
	
	@Value("${todays.gold.rate}")
	private String goldRate;
	
	@GetMapping("/referral/ping")
	public String ping() {
		return "pong"+goldRate;
	}
	
	@GetMapping("referral/getData")
	public String getData() {
		return "getData";
	}
	
	@PostMapping("referral/postData")
	public String insertData() {
		return "insertData";
	}
	
	@DeleteMapping("referral/deleteData")
	public String deleteData() {
		return "deleteData";
	}
}
