package com.revature.banking_application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.revature.banking_application.HomePage;
import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.models.UserNodes;

public class BankDriver extends BankUser {
	
	//static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		
		UserNodes userList = initialList();
		HomePage programStart = new HomePage(userList);
		
		
//		ArrayList<String> listToSend = new ArrayList<String>();
//
//        //use your for each loop to add elements to the list
//        listToSend.add("First element");
//        listToSend.add("Second Element");
//        listToSend.add("Third Element");
//
//        OutputTester gui = new OutputTester(listToSend);

	}

}
