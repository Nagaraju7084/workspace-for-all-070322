package com.springcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcloud.model.Coupon;
import com.springcloud.repository.CouponRepository;

@Service
public class CouponServiceImpl implements CouponService{
	
	@Autowired
	private CouponRepository couponRepository;

	@Override
	public Coupon save(Coupon coupon) {
		return couponRepository.save(coupon);
	}

	@Override
	public Coupon findByCode(String code) {
		return couponRepository.findByCode(code);
	}

}
