package com.boot.datevalidation.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.datevalidation.entity.Order;
import com.boot.datevalidation.repository.OrderRepository;
import com.boot.datevalidation.requestDto.OrderDto;
import com.boot.datevalidation.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public OrderDto save(OrderDto orderDto) {
		return orderToDto(orderRepository.save(dtoToOrder(orderDto)));
	}
	
	public OrderDto orderToDto(Order order) {
		OrderDto orderDto = new OrderDto();
		BeanUtils.copyProperties(order, orderDto);
		return orderDto;
	}
	
	public Order dtoToOrder(OrderDto orderDto) {
		Order order = new Order();
		BeanUtils.copyProperties(orderDto, order);
		return order;
	}

}
