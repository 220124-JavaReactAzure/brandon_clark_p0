package com.revature.banking_application.models;

import java.util.Date;

public class Transaction {
	private double transactionValue;
	private Date date;
	
	public Transaction() {
		super();
	}
	
	public Transaction(double transactionValue, Date date) {
		super();
		this.transactionValue = transactionValue;
		this.date = date;
	}

	public double getTransactionValue() {
		return transactionValue;
	}

	public Date getDate() {
		return date;
	}

}
