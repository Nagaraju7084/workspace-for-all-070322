package com.shopifyme.commandevents.commands;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class CreateInvoiceCommand{

    @TargetAggregateIdentifier
    public final String paymentId;

    public final String orderId;
    
    public final BigDecimal price;
    
    public CreateInvoiceCommand(String paymentId, String orderId, BigDecimal price) {
    	super();
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.price = price;
    }
}
