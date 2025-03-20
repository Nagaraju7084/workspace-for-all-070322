package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.model.Product;
import com.springcloud.restclient.Coupon;
import com.springcloud.restclient.CouponClient;
import com.springcloud.service.ProductService;

@RestController
@RequestMapping("/productapi")
public class ProductController {
	
	@Autowired
	private CouponClient couponClient;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/product",method = RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		Coupon coupon = couponClient.getCoupon(product.getCouponCode());
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return productService.save(product);
	}
	
	@RequestMapping(value = "/product/{name}",method = RequestMethod.GET)
	public Product getProductName(String name) {
		return productService.getProductByName(name);
	}

}
