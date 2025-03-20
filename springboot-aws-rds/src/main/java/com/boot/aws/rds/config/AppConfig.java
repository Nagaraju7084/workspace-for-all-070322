package com.boot.aws.rds.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Configuration
public class AppConfig {
	
	private Gson gson = new Gson();
	
	@Bean
	public DataSource dataSource() {
		AwsSecrets secrets = getSecret();
		return DataSourceBuilder
				.create()
				.url("jdbc:" + secrets.getEngine() + "://" + secrets.getHost() + ":" + secrets.getPort() + "/awsrdsdb")
				.username(secrets.getUsername())
				.password(secrets.getPassword())
				.build();
	}
	
	private AwsSecrets getSecret() {

	    String secretName = "rds-db-credentials";
	    Region region = Region.of("ap-south-1");
	    
	    String secret, decodeBynarySecrete;
	    
	    // Create a Secrets Manager client
	    SecretsManagerClient client = SecretsManagerClient.builder()
	            .region(region)
	            .credentialsProvider(ProfileCredentialsProvider.create())
	            .build();
	    
	    GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
	            .secretId(secretName)
	            .build();

	    GetSecretValueResponse getSecretValueResponse = null;

	    try {
	        getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
	    } catch (Exception e) {
	        throw e;
	    }
	    
	    if(getSecretValueResponse.secretString() != null) {
	    	secret = getSecretValueResponse.secretString();
	    	return gson.fromJson(secret, AwsSecrets.class);
	    }
		return null;
	}
	
}
