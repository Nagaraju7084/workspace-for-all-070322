package com.shopifyme.brands.cqrs.commands;

import java.io.Serializable;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class BrandsCategoryCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String alias;
	
	@TargetAggregateIdentifier
	private long aggregateIdentifier;
	
	private boolean enabled;

}
