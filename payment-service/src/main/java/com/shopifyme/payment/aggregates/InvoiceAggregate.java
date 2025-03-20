package com.shopifyme.payment.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.shopifyme.commandevents.commands.CreateInvoiceCommand;
import com.shopifyme.commandevents.events.InvoiceCreatedEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Aggregate
@Data
@NoArgsConstructor
public class InvoiceAggregate {

    @AggregateIdentifier
    private String paymentId;

    private String orderId;

    private InvoiceStatus invoiceStatus;

    @CommandHandler
    public InvoiceAggregate(CreateInvoiceCommand createInvoiceCommand){
    	System.out.println("in invoice aggregate step-2: converting the command to event by the aggregator");
    	InvoiceCreatedEvent invoiceEvent = new InvoiceCreatedEvent(createInvoiceCommand.getPaymentId(),
    			createInvoiceCommand.getOrderId());
    	System.out.println("in invoice aggregate step-3: publishing the event");
        AggregateLifecycle.apply(invoiceEvent);
    }

    @EventSourcingHandler
    protected void on(InvoiceCreatedEvent invoiceCreatedEvent){
        this.paymentId = invoiceCreatedEvent.paymentId;
        this.orderId = invoiceCreatedEvent.orderId;
        this.invoiceStatus = InvoiceStatus.PAID;
    }
}
