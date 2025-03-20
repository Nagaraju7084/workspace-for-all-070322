package com.shopifyme.categories.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.shopifyme.categories.events.CategoryEvent;
import com.shopifyme.saga.commands.CategoryUpdateSagaCommand;

@Saga
public class CategoryOrchestrator {
	
	@Autowired
	private CommandGateway commandGateway;
	
	@StartSaga
	@SagaEventHandler(associationProperty = "aggregateIdentifier")
	public void updateCategoryTransactionalEvent(CategoryEvent event) {
		System.out.println("i am in category orcestrator");
		System.out.println("associate :\t"+event.getAggregateIdentifier());
		SagaLifecycle.associateWith("CATEGORY_UPDATED", "true");
		CategoryUpdateSagaCommand updateCategoryCommand = 
				new CategoryUpdateSagaCommand(event.getId(), event.getName(), event.getAlias(), event.isEnabled(),event.getAggregateIdentifier());
		commandGateway.send(updateCategoryCommand);
	}
}
