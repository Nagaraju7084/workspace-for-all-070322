package com.boot.aws.sqsqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(
        exclude = {
                org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration.class
        }
)
@RestController
public class SpringbootAwsSqsqueueApplication {
	
	Logger logger = LoggerFactory.getLogger(SpringbootAwsSqsqueueApplication.class);
	
	@Autowired
	private QueueMessagingTemplate messagingTemplate;
	
	@Value("${cloud.aws.end-point}")
	private String endpoint;
	
	@GetMapping("/send/{message}")
	public void sendMessageToQueue(@PathVariable String message) {
		messagingTemplate.send(endpoint, MessageBuilder.withPayload(message).build());
	}
	
	@SqsListener("weshopify-queue")
	public void loadMessageFromSqs(String message) {
		logger.info("message from sqs queue ::{}",message);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsSqsqueueApplication.class, args);
	}

}
