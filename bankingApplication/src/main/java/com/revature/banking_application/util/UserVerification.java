package com.revature.banking_application.util;

import com.revature.banking_application.models.BankUser;

public class UserVerification {
	public static Boolean CheckExistingUsername(String userInput) {
		int existingUser = DatabaseAccess.SearchUsername(userInput);
		if(existingUser != 0) {
			return true;
		}
		return false;
	}
	
	public static int CheckExistingUsernameReturnNumber(String userInput) {
		int existingUser = DatabaseAccess.SearchUsername(userInput);
		if(existingUser != 0) {
			return existingUser;
		}
		return 0;
	}
	
	public static int CheckExistingUsername(String userInput, BankUser currentUser) {
		int existingUser = DatabaseAccess.SearchUsername(userInput);
		if(existingUser == currentUser.getUserID()) {
			return 0;
		}
		return existingUser;
	}
	
	public static Boolean CheckExistingEmail(String userInput) {
		String existingEmail = DatabaseAccess.SearchEmail(userInput);
		if(existingEmail != null) {
			return true;
		}
		return false;
	}
	
	public static Boolean CheckExistingEmail(String userInput, BankUser currentUser) {
		String existingEmail = DatabaseAccess.SearchEmail(userInput);
		if(existingEmail != null && !(existingEmail.trim().equals(currentUser.getEmail().trim()))) {
			return true;
		}
		return false;
	}
	
	public static Boolean CheckMatchingPassword() {
		return false;
	}
	
	public static Boolean CheckCapitalization(String userInput) {
		if(Character.isLowerCase(userInput.charAt(0))) {
			return true;
		}
		return false;
	}
	
	public static Boolean CheckEmailContains(String userInput) {
		if(!(userInput.contains("@"))){
			return true;
		} else if (!(userInput.contains(".com") || userInput.contains(".net") || userInput.contains(".org"))) {
			return true;
		}
	return false;
	}
	
	public static Boolean CheckNumeric(String userInput) {
		for(int i = 0; i<userInput.length(); i++) {
			if(!(Character.isDigit(userInput.charAt(i)))){
				if(userInput.charAt(i) != '.') {
					return true;
				}
			}
		}
		return false;
	}
	
	public static Boolean CheckAllValuesAreNotNull(BankUser currentUser) {
		String blank = "";
		if(currentUser.getAddress().equals(blank)) {
			return true;
		}
		if(currentUser.getSocial().equals(blank)) {
			return true;
		}
		if(currentUser.getState().equals(blank)) {
			return true;
		}
		if(currentUser.getCity().equals(blank)) {
			return true;
		}
		if(currentUser.getZipCode().equals(blank)) {
			return true;
		}
		return false;
	}
	
}
