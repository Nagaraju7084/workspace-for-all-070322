package com.shopifyme.orchestrator.aggregates;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.shopifyme.commandevents.commands.CreateOrderCommand;
import com.shopifyme.commandevents.commands.UpdateOrderStatusCommand;
import com.shopifyme.commandevents.events.OrderCreatedEvent;
import com.shopifyme.commandevents.events.OrderUpdateEvent;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;

    private ItemType itemType;

    private BigDecimal price;

    private String currency;

    private OrderStatus orderStatus;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand){
    	System.out.println("executing  CreateOrderCommand");
        AggregateLifecycle.apply(new OrderCreatedEvent(createOrderCommand.orderId, createOrderCommand.itemType,
                createOrderCommand.price, createOrderCommand.currency, createOrderCommand.orderStatus));
        
        System.out.println("Dispatching   OrderCreatedEvent");
    }

    @EventSourcingHandler
    protected void on(OrderCreatedEvent orderCreatedEvent){
    	System.out.println("Received OrderCreatedEvent ");
        this.orderId = orderCreatedEvent.orderId;
        this.itemType = ItemType.valueOf(orderCreatedEvent.itemType);
        this.price = orderCreatedEvent.price;
        this.currency = orderCreatedEvent.currency;
        this.orderStatus = OrderStatus.valueOf(orderCreatedEvent.orderStatus);
    }

    @CommandHandler
    protected void on(UpdateOrderStatusCommand updateOrderStatusCommand){
        AggregateLifecycle.apply(new OrderUpdateEvent(updateOrderStatusCommand.orderId, updateOrderStatusCommand.orderStatus));
    }

    @EventSourcingHandler
    protected void on(OrderUpdateEvent orderUpdatedEvent){
        this.orderId = orderId;
        this.orderStatus = OrderStatus.valueOf(orderUpdatedEvent.orderStatus);
    }
}
