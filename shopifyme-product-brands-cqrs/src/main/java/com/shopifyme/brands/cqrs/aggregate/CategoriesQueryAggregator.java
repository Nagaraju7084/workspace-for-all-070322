package com.shopifyme.brands.cqrs.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopifyme.brands.cqrs.commands.BrandsCategoryCommand;
import com.shopifyme.brands.cqrs.events.BrandsCategoryEvent;

@Aggregate
public class CategoriesQueryAggregator {
	
	@AggregateIdentifier
	private long id;
	
	@CommandHandler
	public CategoriesQueryAggregator(BrandsCategoryCommand command) {
		System.out.println("i am in product brands ");
		BrandsCategoryEvent event = new BrandsCategoryEvent();
		this.id = command.getAggregateIdentifier();
		BeanUtils.copyProperties(command, event);
		AggregateLifecycle.apply(event);
	}
	
	@EventHandler
	public void brandsCategoryUpdateEvent(BrandsCategoryEvent event) {
		System.out.println("brands event recieved is:\t"+event.getAggregateIdentifier());
		ObjectMapper mapper = new ObjectMapper();
		
	}
}
