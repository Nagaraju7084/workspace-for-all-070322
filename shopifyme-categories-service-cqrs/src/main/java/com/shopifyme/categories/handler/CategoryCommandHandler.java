package com.shopifyme.categories.handler;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopifyme.categories.bean.CategoryBean;
import com.shopifyme.categories.command.CategoryCommand;

@Component
public class CategoryCommandHandler {
	
	@Autowired
	private CommandGateway commandGateway;
	
	public CategoryBean publishCategoryUpdates(CategoryBean categoryBean) {
		CategoryCommand catCommand = new CategoryCommand(categoryBean.getId(), categoryBean.getName(), categoryBean.getAlias(), categoryBean.isEnabled());
		CompletableFuture<String> future = commandGateway.send(catCommand);
		if(null != future && future.isDone()) {
			System.out.println("in CategoryCommandHandler : command converted to event -> event published to broker");
		}else if(future.isCompletedExceptionally()){
			System.out.println("in CategoryCommandHandler : command converted to event -> event published to broker exceptionally");
		}else if(future.isCancelled()){
			System.out.println("in CategoryCommandHandler : command converted to event might failed or event published to broker might failed");
		}
		return null;
	}

}
