package com.shopifyme.commandevents.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class UpdateOrderStatusCommand {

    @TargetAggregateIdentifier
    public final String orderId;

    public final String orderStatus;

    public UpdateOrderStatusCommand(String orderId, String orderStatus) {
    	super();
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
