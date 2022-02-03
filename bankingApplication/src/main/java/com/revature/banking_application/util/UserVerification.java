package com.revature.banking_application.util;

public class UserVerification {
	public static Boolean CheckExistingUsername(String userInput) {
		String existingUser = DatabaseAccess.SearchUsername(userInput);
		if(existingUser != null) {
			return true;
		}
		return false;
	}
	
	public static Boolean CheckExistingEmail(String userInput) {
		String existingEmail = DatabaseAccess.SearchEmail(userInput);
		if(existingEmail != null) {
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
}
