package com.myshopify.beans;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class CustomerTest {

	public static void main(String[] args) {

		Resource resource = new ClassPathResource("myshopify-beans.xml");
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		Customer customer = beanFactory.getBean(Customer.class);
		System.out.println(customer.getId());
		System.out.println(customer.getName());
		System.out.println("hash code : \t"+customer.hashCode());
		
	}

}
