package com.myshopify.ecommerce;

import com.myshopify.ecommerce.tightcoupling.CreditCardAccount;
import com.myshopify.ecommerce.tightcoupling.LoanAccount;

public class AccountsFactory {
	
	public static Accounts getMyAccount(String accountType) {
		Accounts acc = null;
		if(null != accountType && accountType.equals(AccountsType.LOAN_ACCOUNT_NUMBER.name())) {
			acc = new LoanAccount();
		}
		if(null != accountType && accountType.equals(AccountsType.CREDIT_CARD_NUMBER.name())) {
			acc = new CreditCardAccount();
		}
		return acc;
	}
	
	public static Accounts getMyCreditCardAccount() {
		Accounts acc = null;
		acc = new CreditCardAccount();
		return acc;
	}
	
	public static Accounts getMyLoanAccount() {
		Accounts acc = null;
		acc = new LoanAccount();
		return acc;
	}

}
