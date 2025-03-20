package com.springcloud.restclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("COUPON-SERVICE")
@RibbonClient("COUPON-SERVICE")
public interface CouponClient {
	
	@GetMapping(value = "/couponapi/coupons/{code}")
	Coupon getCoupon(@PathVariable("code")String code);

}
