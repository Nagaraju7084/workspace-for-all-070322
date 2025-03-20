package com.shopifyme.commandevents.events;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderCreatedEvent implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final String orderId;

    public final String itemType;

    public final BigDecimal price;

    public final String currency;

    public final String orderStatus;

    public OrderCreatedEvent(String orderId, String itemType, BigDecimal price, String currency, String orderStatus) {
    	super();
        this.orderId = orderId;
        this.itemType = itemType;
        this.price = price;
        this.currency = currency;
        this.orderStatus = orderStatus;
    }

	@Override
	public String toString() {
		return "OrderCreatedEvent [orderId=" + orderId + ", itemType=" + itemType + ", price=" + price + ", currency="
				+ currency + ", orderStatus=" + orderStatus + "]";
	}
    
    
    
}
