	package com.myshopify.beans;

import java.io.Serializable;

public class CreditCardAccount implements Serializable{
	
	private int cardId;
	private double cardLimit;
	private String accountType = "Credit Card Account";
	
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
