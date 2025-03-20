package com.myshopify.ecommerce.tightcoupling;

import java.io.Serializable;

import com.myshopify.ecommerce.Accounts;

public class LoanAccount implements Serializable, Accounts{
	
	private int loanId;
	private double laonAmount;
	private String accountType = "Loan Account";
	
	public boolean doPayment(double emi) {
		boolean isPaymentDone = false;
		if(emi != 0d) {
			isPaymentDone = true;
		}
		return isPaymentDone;
	}
	
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public double getLaonAmount() {
		return laonAmount;
	}
	public void setLaonAmount(double laonAmount) {
		this.laonAmount = laonAmount;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	

}
