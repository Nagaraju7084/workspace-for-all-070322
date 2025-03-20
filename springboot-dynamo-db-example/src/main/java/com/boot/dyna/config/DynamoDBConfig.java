package com.boot.dyna.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDBConfig {
	
	private static final String SERVICE_ENDPOINT = "dynamodb.ap-south-1.amazonaws.com";
	private static final String REGION = "ap-south-1";
	private static final String ACCESS_KEY = "AKIAT2S7MNE3TYIPTJO6";
	private static final String SECRETE_KEY = "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";

	@Bean
	public DynamoDBMapper mapper() {
		return new DynamoDBMapper(amzonDynamoDBConfig());
	}

	private AmazonDynamoDB amzonDynamoDBConfig() {
		return AmazonDynamoDBAsyncClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(SERVICE_ENDPOINT, REGION))
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials(ACCESS_KEY, SECRETE_KEY))).build();
	}
}
