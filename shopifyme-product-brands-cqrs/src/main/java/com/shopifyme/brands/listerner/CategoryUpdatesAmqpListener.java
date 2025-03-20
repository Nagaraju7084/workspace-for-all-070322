package com.shopifyme.brands.listerner;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopifyme.brands.cqrs.commands.BrandsCategoryCommand;
import com.shopifyme.brands.repositories.BrandRepository;

@Configuration
public class CategoryUpdatesAmqpListener {
	
	@Autowired
	private RabbitTemplate amqpTemplate;
	
	@Autowired
	private BrandRepository brandRepo;
	
	@Autowired
	private CommandGateway commandGateway;
	
	@RabbitListener(queues = "${amqp.services.category-service-queue}")
	public void recieveCategoryUpdates(String categoryEventJsonData) {
		
		//{"id":2,"name":"kids","alias":"kids","aggregateIdentifier":"7352262760150639728","enabled":true}
		System.out.println("event recieved from queue is:\t"+categoryEventJsonData);
		ObjectMapper mapper = new ObjectMapper();
		try {
			BrandsCategoryCommand brandsCommand = mapper.readValue(categoryEventJsonData, BrandsCategoryCommand.class);
			
			  CompletableFuture<String> futureResp = commandGateway.send(brandsCommand);
			  if(futureResp.isDone()) {
			  System.out.println("command successfully taken by the aggregate"); }
			 
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
