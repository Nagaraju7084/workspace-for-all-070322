package com.curd.backend.service.impl;

import org.springframework.stereotype.Service;

import com.curd.backend.service.CounterService;

@Service
public class CounterServiceImpl implements CounterService {

	volatile int counter = 0;
	
	@Override
	public int getCounter() {
		return counter;
	}

	@Override
	public int incrementCounter() {
		return ++counter;
	}

}
