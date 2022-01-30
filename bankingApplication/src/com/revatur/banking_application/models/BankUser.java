package com.revatur.banking_application.models;

public class BankUser {
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	public BankUser() {
		super();
	}

	public BankUser(String username, String firstName, String lastName, String email, String password) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toFileString() {
		StringBuilder buildFileString = new StringBuilder();
		
		buildFileString.append(firstName).append(":")
						.append(lastName).append(":")
						.append(email).append(":")
						.append(username).append(":")
						.append(password);
		
		return buildFileString.toString();
	}
	
	
	
}
