package com.shopifyme.categories.eventsources;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopifyme.categories.aggregate.CategoryAggregate;
import com.shopifyme.categories.events.CategoryEvent;

/**
 * 
 * @author nkailasa
 *
 */
@Component
public class ExternalEventSourcingHandler {
	
	@Autowired
	private EventSourcingRepository<CategoryAggregate> eventStoreRepo;
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Autowired
	private RabbitTemplate template;

	@Value("${spring.rabbitmq.template.exchange}")
	private String exchange;
	
	@Value("${spring.rabbitmq.template.routing-key}")
	private String routingKey;
	
	@EventSourcingHandler
	@Order(value = 2)
	public void publishEventToAmqpBroker(CategoryEvent categoryEvent) {
		System.out.println("ExternalEventSourcingHandler:: publishEventToAmqpBroker "+categoryEvent.toString());
		CategoryAggregate catAggregate = eventStoreRepo.load(categoryEvent.getAggregateIdentifier()).getWrappedAggregate().getAggregateRoot();
		//if(null != catAggregate && String.valueOf(catAggregate.getId()).equals(categoryEvent.getAggregateIdentifier())) {
			
			System.out.println("exchange is:\t"+exchange);
			System.out.println("routing key is:\t"+routingKey);
			
			//publish to rabitmq broker
			//amqpTemplate.convertAndSend(exchange, routingKey, categoryEvent);
			ObjectMapper mapper = new ObjectMapper();
			try {
				this.template.convertAndSend(mapper.writeValueAsString(categoryEvent));
			} catch (JsonProcessingException | AmqpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}
	
	//@EventSourcingHandler
	public void publishEventToMQTTBroker(CategoryEvent categoryEvent) {
		System.out.println("ExternalEventSourcingHandler:: publishEventToMQTTBroker "+categoryEvent.toString());
	}
	
	//@EventSourcingHandler
	public void publishEventToFileStorage(CategoryEvent categoryEvent) {
		System.out.println("ExternalEventSourcingHandler:: publishEventToFileStorage "+categoryEvent.toString());
	}
	
	//@EventSourcingHandler
	public void publishEventToCacheStorage(CategoryEvent categoryEvent) {
		System.out.println("ExternalEventSourcingHandler:: publishEventToCacheStorage "+categoryEvent.toString());
	}
	
}
