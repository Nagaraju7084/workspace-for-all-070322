package com.myshopify.ecommerce.tightcoupling;

import java.io.Serializable;

import com.myshopify.ecommerce.Accounts;
import com.myshopify.ecommerce.AccountsImpl;

public class Customer implements Serializable {
	private int id;
	private String name;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public boolean payCreditCardBill(double cardBill) {
		//CreditCardAccount creditCard = new CreditCardAccount();
		Accounts accounts = new AccountsImpl();
		return accounts.doPayment(cardBill);
	}
	
	public boolean payLoanEMI(double emiBill) {
		LoanAccount loanAccount = new LoanAccount();
		return loanAccount.doPayment(emiBill);
	}

}
