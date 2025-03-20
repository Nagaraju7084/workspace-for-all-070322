package com.shopifyme.shipment.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.shopifyme.commandevents.commands.CreateShippingCommand;
import com.shopifyme.commandevents.events.OrderShippedEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Aggregate
@Data
@NoArgsConstructor
public class ShippingAggregate {

    @AggregateIdentifier
    private String shippingId;

    private String orderId;

    private String paymentId;

    @CommandHandler
    public ShippingAggregate(CreateShippingCommand createShippingCommand){
    	System.out.println("in shipping aggregate step-2: converting the command to event by the aggregator");
    	OrderShippedEvent orderShippedEvent = new OrderShippedEvent(createShippingCommand.getShippingId(),
        		createShippingCommand.getOrderId(), createShippingCommand.getPaymentId());
    	System.out.println("in shipping aggregate step-3: publishing the event");
        AggregateLifecycle.apply(orderShippedEvent);
    }

    @EventSourcingHandler
    protected void on(OrderShippedEvent orderShippedEvent){
        this.shippingId = orderShippedEvent.shippingId;
        this.orderId = orderShippedEvent.orderId;
    }
}
