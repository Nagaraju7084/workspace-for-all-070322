package com.boot.aws.sns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class AwsSnsConfig {
	
	private static final String ACCESS_KEY = "AKIAT2S7MNE3TYIPTJO6";
	private static final String SECRET_KEY = "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";

	@Primary
	@Bean
	public AmazonSNSClient getSnsClient() {
		return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
				.withCredentials(new AWSStaticCredentialsProvider(
				new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
				.build();
	}
	
}
