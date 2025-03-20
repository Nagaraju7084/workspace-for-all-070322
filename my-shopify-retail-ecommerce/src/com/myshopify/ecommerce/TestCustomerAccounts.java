package com.myshopify.ecommerce;

import com.myshopify.ecommerce.tightcoupling.Customer;

public class TestCustomerAccounts {

	public static void main(String[] args) {
		
		Customer customer = new Customer();
		boolean isBillPaid = customer.payCreditCardBill(500);
		System.out.println("does payment done?"+isBillPaid);

	}

}
