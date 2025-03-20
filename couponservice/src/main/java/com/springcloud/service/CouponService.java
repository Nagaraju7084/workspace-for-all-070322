package com.springcloud.service;

import com.springcloud.model.Coupon;

public interface CouponService {

	Coupon save(Coupon coupon);

	Coupon findByCode(String code);

}
