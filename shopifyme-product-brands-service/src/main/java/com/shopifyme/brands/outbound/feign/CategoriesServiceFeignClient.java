package com.shopifyme.brands.outbound.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Category-Service-Client", url = "${outbound.api.categories.uri}")
public interface CategoriesServiceFeignClient {
	
	@GetMapping
	public ResponseEntity<String> fetchAllCategories(@RequestHeader Map<String,String> headerMap, @RequestParam("sortDir") String sortDir);
}
