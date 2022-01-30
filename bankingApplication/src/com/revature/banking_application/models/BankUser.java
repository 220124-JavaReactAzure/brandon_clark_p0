package com.revature.banking_application.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankUser {
	static UserNodes userList = new UserNodes();
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toFileString() {
		StringBuilder buildFileString = new StringBuilder();
		
		buildFileString.append(username).append(":")
					   .append(firstName).append(":")
					   .append(lastName).append(":")
					   .append(email).append(":")
					   .append(password);
		
		return buildFileString.toString();
	}
	
	public void addToList(BankUser userToInput) {
		userList.addNode(userToInput);
	}
	
	
	
	
	public static UserNodes initialList(){
		Path dataPath = Paths.get("C:\\Users\\silve\\Desktop\\coding stuff\\brandon_clark_p0\\bankingApplication\\src\\com\\revature\\banking_application\\resources\\data.txt");
		List<String> fileData;
		
		try {
			fileData = Files.readAllLines(dataPath);
			for(int i=0;i<fileData.size();i++){
				if(fileData.get(i).trim().isEmpty()) {
					//System.out.println("empty line");
				} else {
					String[] savedInformation = fileData.get(i).split(":");
					
					String savedUsername = savedInformation[0];
					String savedFirstName = savedInformation[1];
					String savedLastName = savedInformation[2];
					String savedEmail = savedInformation[3];
					String savedPassword = savedInformation[4];
				
//					System.out.println(savedUsername);
//					System.out.println(savedFirstName);
//					System.out.println(savedLastName);
//					System.out.println(savedEmail);
//					System.out.println(savedPassword);
				
					BankUser savedUser = new BankUser(savedUsername, savedFirstName, savedLastName, savedEmail, savedPassword);
					userList.addNode(savedUser);
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return userList;
	}
	
}
