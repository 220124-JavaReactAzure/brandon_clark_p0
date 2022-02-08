package com.revature.banking_application;



import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.outer.HomePage;
import com.revature.banking_application.util.logging.Logger;

public class BankDriver extends BankUser {
	
	private static Logger logger;

	public static void main(String[] args) {
		logger = Logger.getLogger(true);
		logger.log("Application Started");
		new HomePage();
	}

}
