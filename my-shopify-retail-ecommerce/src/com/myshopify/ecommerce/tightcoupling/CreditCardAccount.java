	package com.myshopify.ecommerce.tightcoupling;

import java.io.Serializable;

import com.myshopify.ecommerce.Accounts;

public class CreditCardAccount implements Serializable, Accounts{
	
	private int cardId;
	private double cardLimit;
	private String accountType = "Credit Card Account";
	
	public boolean doPayment(double cardBill) {
		boolean isPaymentDone = false;
		if(cardBill != 0d) {
			isPaymentDone = true;
		}
		return isPaymentDone;
	}
	
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public double getCardLimit() {
		return cardLimit;
	}
	public void setCardLimit(double cardLimit) {
		this.cardLimit = cardLimit;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	

}
