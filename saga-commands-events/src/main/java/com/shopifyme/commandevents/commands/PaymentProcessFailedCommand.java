package com.shopifyme.commandevents.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class PaymentProcessFailedCommand{

    @TargetAggregateIdentifier
    public final String paymentId;

    public final String orderId;

    public PaymentProcessFailedCommand(String paymentId, String orderId) {
    	super();
        this.paymentId = paymentId;
        this.orderId = orderId;
    }
}
