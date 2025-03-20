package com.boot.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.hotel.service.KafkaService;

@RestController
@RequestMapping("/location")
public class KafkaController {

	@Autowired
	private KafkaService kafkaService;
	
	@PostMapping("/update")
	public ResponseEntity<?> updateLocation(){
		for(int i=1; i<1000; i++) {
			kafkaService.updateLocation("( "+Math.round(Math.random()*100)+", "+Math.round(Math.random()*100)+" "+")");
		}
		return new ResponseEntity<>("location updated...", HttpStatus.OK);
	}
	
}
