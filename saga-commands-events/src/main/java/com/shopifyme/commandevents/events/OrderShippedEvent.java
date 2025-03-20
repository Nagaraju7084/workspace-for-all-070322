package com.shopifyme.commandevents.events;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderShippedEvent implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final String shippingId;

    public final String orderId;

    public final String paymentId;

    public OrderShippedEvent(String shippingId, String orderId, String paymentId) {
    	super();
        this.shippingId = shippingId;
        this.orderId = orderId;
        this.paymentId = paymentId;
    }

	@Override
	public String toString() {
		return "OrderShippedEvent [shippingId=" + shippingId + ", orderId=" + orderId + ", paymentId=" + paymentId
				+ "]";
	}
    
    
}
