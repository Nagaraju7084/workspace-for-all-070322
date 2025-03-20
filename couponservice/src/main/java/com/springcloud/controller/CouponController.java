package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.model.Coupon;
import com.springcloud.service.CouponService;

@RestController
@RequestMapping("/couponapi")
public class CouponController {
	
	@Autowired
	private CouponService couponService;
	
	@RequestMapping(value = "/coupon",method = RequestMethod.POST)
	public Coupon create(@RequestBody Coupon coupon) {
		return couponService.save(coupon);
	}
	
	@RequestMapping(value = "/coupons/{code}",method = RequestMethod.GET)
	public Coupon getCoupon(@PathVariable("code")String code) {
		System.out.println("server 2");
		return couponService.findByCode(code);
	}

}
