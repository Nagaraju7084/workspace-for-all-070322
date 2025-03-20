package com.shopifyme.commandevents.events;

import java.io.Serializable;

import lombok.Data;

@Data
public class PaymentProcessFailedEvent implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final String paymentId;

    public final String orderId;

    public PaymentProcessFailedEvent(String paymentId, String orderId) {
    	super();
        this.paymentId = paymentId;
        this.orderId = orderId;
    }

	@Override
	public String toString() {
		return "InvoiceCreatedEvent [paymentId=" + paymentId + ", orderId=" + orderId + "]";
	}
    
    
}
