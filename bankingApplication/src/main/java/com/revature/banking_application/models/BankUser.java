package com.revature.banking_application.models;


public class BankUser {
	static AccountNodes userList = new AccountNodes();
	private int userID;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String social;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private int jointUserID;
	
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

	public BankUser(int userID, String username, String firstName, String lastName, String email, String password) {
		super();
		this.userID = userID;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public BankUser(int userID, String username, String firstName, String lastName, String email, String password, 
			String social, String address, String city, String state, String zipCode, int jointUserID) {
		super();
		this.userID = userID;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.social = social;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.jointUserID = jointUserID;
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

	public int getUserID() {
		return userID;
	}

	public String getSocial() {
		return social;
	}

	public void setSocial(String social) {
		this.social = social;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getJointUserID() {
		return jointUserID;
	}

	public void setJointUserID(int jointUserID) {
		this.jointUserID = jointUserID;
	}
	
	
	
//	public String toFileString() {
//		StringBuilder buildFileString = new StringBuilder();
//		
//		buildFileString.append(username).append(":")
//					   .append(firstName).append(":")
//					   .append(lastName).append(":")
//					   .append(email).append(":")
//					   .append(password);
//		
//		return buildFileString.toString();
//	}
//	
//	public void addToList(BankUser userToInput) {
//		userList.addNode(userToInput);
//	}
//	
//	public static UserNodes initialList(){
//		Path dataPath = Paths.get("C:\\Users\\silve\\Desktop\\coding stuff\\brandon_clark_p0\\bankingApplication\\src\\main\\resources\\data.txt");
//		List<String> fileData;
//		
//		try {
//			fileData = Files.readAllLines(dataPath);
//			for(int i=0;i<fileData.size();i++){
//				if(fileData.get(i).trim().isEmpty()) {
//					//System.out.println("empty line");
//				} else {
//					String[] savedInformation = fileData.get(i).split(":");
//					
//					String savedUsername = savedInformation[0];
//					String savedFirstName = savedInformation[1];
//					String savedLastName = savedInformation[2];
//					String savedEmail = savedInformation[3];
//					String savedPassword = savedInformation[4];
//				
////					System.out.println(savedUsername);
////					System.out.println(savedFirstName);
////					System.out.println(savedLastName);
////					System.out.println(savedEmail);
////					System.out.println(savedPassword);
//				
//					BankUser savedUser = new BankUser(savedUsername, savedFirstName, savedLastName, savedEmail, savedPassword);
//					userList.addNode(savedUser);
//				}
//			}
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		return userList;
//	}
	
}
