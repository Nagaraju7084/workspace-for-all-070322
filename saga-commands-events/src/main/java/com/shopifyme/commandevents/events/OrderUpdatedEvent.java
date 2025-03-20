package com.shopifyme.commandevents.events;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderUpdatedEvent implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final String orderId;

    public final String orderStatus;

    public OrderUpdatedEvent(String orderId, String orderStatus) {
    	super();
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

	@Override
	public String toString() {
		return "OrderUpdatedEvent [orderId=" + orderId + ", orderStatus=" + orderStatus + "]";
	}
    
    
}
