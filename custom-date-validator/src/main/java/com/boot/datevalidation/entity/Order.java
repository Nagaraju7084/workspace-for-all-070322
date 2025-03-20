package com.boot.datevalidation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Order {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	private String orderDate;
}
