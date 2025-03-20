package com.shopifyme.categories.aggregate;

import org.apache.commons.lang3.RandomUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.core.annotation.Order;

import com.shopifyme.categories.command.CategoryCommand;
import com.shopifyme.categories.events.CategoryEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Aggregate
@Data
public class CategoryAggregate {
	
	@AggregateIdentifier
	private long id;
	
	@CommandHandler
	public CategoryAggregate(CategoryCommand categoryCommand) {
		System.out.println("step-2: converting the command to event by the aggregator");
		this.id = RandomUtils.nextLong();
		CategoryEvent categoryEvent = new CategoryEvent(categoryCommand.getId(),
				categoryCommand.getName(), categoryCommand.getAlias(),categoryCommand.isEnabled(),String.valueOf(id));
		System.out.println("step-3: publishing the event");
		AggregateLifecycle.apply(categoryEvent);
	}
	
	/**
	 * broker publisher event handler
	 * @param categoryEvent
	 */
	@EventHandler
	@Order(value = 1)
	public void primaryEventSourcingHandler(CategoryEvent categoryEvent) {
		/**
		 * publish this event to broker to take it by the
		 * other consumers, also store this event to event store
		 */
		System.out.println("step-4: in primaryEventSourcingHandler event data is: "+categoryEvent.toString());
	}
	
	/**
	 * event store event handler
	 * @param categoryEvent
	 */
	@EventHandler
	public void secandaryEventSourcingHandler(CategoryEvent categoryEvent) {
		
		/**
		 * publish this event to broker to take it by the
		 * other consumers, also store this event to event store
		 */
		System.out.println("step-4: in secandaryEventSourcingHandler event data is: "+categoryEvent.toString());
	}
}
