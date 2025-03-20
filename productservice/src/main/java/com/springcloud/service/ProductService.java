package com.springcloud.service;

import com.springcloud.model.Product;

public interface ProductService {

	Product save(Product product);

	Product getProductByName(String name);

}
