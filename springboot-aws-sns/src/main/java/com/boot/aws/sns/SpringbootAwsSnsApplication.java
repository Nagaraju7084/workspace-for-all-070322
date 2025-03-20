package com.boot.aws.sns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

@SpringBootApplication(
        exclude = {
                org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration.class
        }
)
@RestController
public class SpringbootAwsSnsApplication {

	@Autowired
	private AmazonSNSClient snsClient;
	
	String TOPIC_URN = "arn:aws:sns:ap-south-1:263266789687:weshopify-topic";
	
	@GetMapping("/addSubscription/{email}")
	public String addSubscription(@PathVariable String email) {
		SubscribeRequest request = new SubscribeRequest(TOPIC_URN, "email", email);
		snsClient.subscribe(request);
		return "Subscription request is pending. To confirm the subscription,"
				+ "check your email : "+email;
	}
	
	@GetMapping("/sendNotification")
	public String publishMessageToTopic() {
		PublishRequest publishRequest = new PublishRequest(TOPIC_URN, buildEmailBody(), "Notification : Network connectivity issue");
		snsClient.publish(publishRequest);
		return "Notification sent successfully...";
	}
	
	private String buildEmailBody() {
		return "Notification will be sent out as soon as the issue is resolved...";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsSnsApplication.class, args);
		
	}

}
