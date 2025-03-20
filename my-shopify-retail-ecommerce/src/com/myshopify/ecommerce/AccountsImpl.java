package com.myshopify.ecommerce;

public class AccountsImpl implements Accounts {

	//CreditCardAccount creditCard = new CreditCardAccount();
	
	@Override
	public boolean doPayment(double bill) {
		
		//Accounts account = AccountsFactory
			//				.getMyAccount(AccountsType.CREDIT_CARD_NUMBER.name());
		Accounts account = AccountsFactory.getMyCreditCardAccount();
		return account.doPayment(bill);
	}

}
