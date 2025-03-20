package com.shopifyme.saga.commands;

import lombok.Data;

@Data
public class CategoryUpdateSagaCommand {

	private final int id;
	
	private final String name;
	
	private final String alias;
	
	private final String aggregateIdentifier;
	
	//private MultipartFile fileImage;
	
	private final boolean enabled;

	public CategoryUpdateSagaCommand(int id, String name, String alias, boolean enabled, String aggregateIdentifier) {
		super();
		this.id = id;
		this.name = name;
		this.alias = alias;
		this.enabled = enabled;
		this.aggregateIdentifier = aggregateIdentifier;
	}
	
	
	
}
