package com.enduser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumerConfiguration {
	
	@KafkaListener(topics = AppConstants.LOCATION_UPDATE_TOPTIC, groupId = AppConstants.GROUP_ID)
	public void updateLocation(String value) {
		System.out.println(value);
	}
}
