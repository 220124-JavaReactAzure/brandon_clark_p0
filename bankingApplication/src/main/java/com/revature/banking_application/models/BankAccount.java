package com.revature.banking_application.models;

public class BankAccount {
	private int userAccountID;
	private int userID;
	private int jointUserID;
	private String accountNickname;
	private int userAccountType;
	private double accountValue;
	
	
	public BankAccount() {
		super();
	}
	
	public BankAccount(int userID, int userAccountType, String accountNickname, double accountValue) {
		super();
		this.userID = userID;
		this.userAccountType = userAccountType;
		this.accountNickname = accountNickname;
		this.accountValue = accountValue;
	}

	public BankAccount(int userID, int jointUserID, int userAccountType, String accountNickname,  double accountValue) {
		super();
		this.userID = userID;
		this.jointUserID = jointUserID;
		this.accountNickname = accountNickname;
		this.userAccountType = userAccountType;
		this.accountValue = accountValue;
	}

	public BankAccount(int userAccountID, int userID, int jointUserID, int userAccountType, String accountNickname, double accountValue) {
		super();
		this.userAccountID = userAccountID;
		this.userID = userID;
		this.jointUserID = jointUserID;
		this.userAccountType = userAccountType;
		this.accountNickname = accountNickname;
		this.accountValue = accountValue;
	}

	public int getUserAccountID() {
		return userAccountID;
	}

	public int getUserID() {
		return userID;
	}

	public int getJointUserID() {
		return jointUserID;
	}

	public String getAccountNickname() {
		return accountNickname;
	}

	public void setAccountNickname(String accountNickname) {
		this.accountNickname = accountNickname;
	}

	public int getUserAccountType() {
		return userAccountType;
	}

	public double getAccountValue() {
		return accountValue;
	}

	public void setAccountValue(double accountValue) {
		this.accountValue = accountValue;
	}


}
