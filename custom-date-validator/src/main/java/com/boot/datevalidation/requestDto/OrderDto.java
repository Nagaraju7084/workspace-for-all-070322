package com.boot.datevalidation.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
	
	private Integer orderId;
	private String orderDate;
	
}
