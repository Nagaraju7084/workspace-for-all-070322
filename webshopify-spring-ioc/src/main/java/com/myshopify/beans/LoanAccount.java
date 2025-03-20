package com.myshopify.beans;

import java.io.Serializable;

public class LoanAccount implements Serializable{
	
	private int loanId;
	private double laonAmount;
	private String accountType = "Loan Account";
	
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
