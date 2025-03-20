package com.boot.elk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.elk.util.AppUtil;

@RestController
@RequestMapping("/payment")
public class PaymentRestController {
	
	private static final Logger log = LoggerFactory.getLogger(PaymentRestController.class);
	
	@GetMapping("/doPay")
	public String doPayment() {
		log.info("entered into payment process!");
		try {
			log.info("payment about to start");
			throw new RuntimeException("no balance exception");
			
		}catch(Exception e) {
			log.info("unable to process payment"+e.getMessage());
			e.printStackTrace();
			log.error("exception -" + AppUtil.getLogSupport(e));
		}
		return "success";
	}
}
