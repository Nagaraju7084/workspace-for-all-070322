package com.shopifyme.brands.cqrs.events;

import java.io.Serializable;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class BrandsCategoryEvent implements Serializable {

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
