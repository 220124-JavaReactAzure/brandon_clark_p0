package com.revature.banking_application;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.models.UserNodes;

public class RegisterPage {
		public RegisterPage(UserNodes userList) {
			
//			Path dataPath = Paths.get("C:\\Users\\silve\\Desktop\\coding stuff\\brandon_clark_p0\\bankingApplication\\src\\com\\revature\\banking_application\\resources\\data.txt");
//			List<String> fileData;
//			try {
//				fileData = Files.readAllLines(dataPath);
//				for(int i=0;i<fileData.size();i++){
//					System.out.println(fileData.get(i));
//				}
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			
			final JFrame registrationFrame = new JFrame();
	        registrationFrame.setTitle("Silver Banking");
	        registrationFrame.setSize(500, 500);
	        registrationFrame.setLocation(550, 400);
	        
	        JPanel gui = new JPanel(new GridLayout(0,2,1,10));
	        
	        String title = "Register a New User";
	        Border border = BorderFactory.createTitledBorder(title);
	        gui.setBorder(border);
	        
	        JLabel firstName = new JLabel("First Name: ");
	        gui.add(firstName);
	        
	        JTextField enterFirstName = new JTextField(null, 20);
	        enterFirstName.setBorder(null);
	        gui.add(enterFirstName);
	        
	        JLabel lastName = new JLabel("Last Name: ");
	        gui.add(lastName);
	        
	        JTextField enterLastName = new JTextField(null, 20);
	        enterLastName.setBorder(null);
	        gui.add(enterLastName);
	        
	        JLabel email = new JLabel("Email Address: ");
	        gui.add(email);
	        
	        JTextField enterEmail = new JTextField(null, 20);
	        enterEmail.setBorder(null);
	        gui.add(enterEmail);
	        
	        JLabel username = new JLabel("Username: ");
	        gui.add(username);
	        
	        JTextField enterUsername = new JTextField(null, 20);
	        enterUsername.setBorder(null);
	        gui.add(enterUsername);
	        
	        JLabel password = new JLabel("Password: ");
	        gui.add(password);
	        
	        JPasswordField enterPassword = new JPasswordField(null, 20);
	        enterPassword.setBorder(null);
	        gui.add(enterPassword);
	        
	        JLabel confirmPassword = new JLabel("Confirm Password: ");
	        gui.add(confirmPassword);
	        
	        JPasswordField enterConfirmPassword = new JPasswordField(null, 20);
	        enterConfirmPassword.setBorder(null);
	        gui.add(enterConfirmPassword);
	        
	        Button returnToHome = new Button("Return");
	        gui.add(returnToHome);
	        returnToHome.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	HomePage hp = new HomePage(userList);
	            	registrationFrame.dispose();
	            	//JOptionPane.showMessageDialog(null, "register");
	            }
	        });
	        
	        Button submit = new Button("Submit");
	        gui.add(submit);
	        submit.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				@Override
	            public void actionPerformed(ActionEvent e) {
					if (enterPassword.getText().trim().isEmpty() || 
	            		enterConfirmPassword.getText().trim().isEmpty() || 
	            		enterUsername.getText().trim().isEmpty() || 
	            		enterLastName.getText().trim().isEmpty() ||
	            		enterFirstName.getText().trim().isEmpty() ||
	            		enterEmail.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null,"Please fill out all information fields.");
					} else if(Character.isLowerCase(enterFirstName.getText().charAt(0))) {
						JOptionPane.showMessageDialog(null,"Please have the first letter of your first name capitalized.");
					} else if (Character.isLowerCase(enterLastName.getText().charAt(0))){
						JOptionPane.showMessageDialog(null,"Please have the first letter of your last name capitalized.");
					} else if (!(enterEmail.getText().contains("@"))){
						JOptionPane.showMessageDialog(null,"Please make sure the email address is valid.");
					} else if(!(enterEmail.getText().contains(".com") || enterEmail.getText().contains(".net") || enterEmail.getText().contains(".org"))) {
						JOptionPane.showMessageDialog(null,"Please make sure the email address is valid.");
				    } else if(!(enterPassword.getText().equals(enterConfirmPassword.getText()))) {
						JOptionPane.showMessageDialog(null,"Please enter matching passwords.");
					} else if(userList.unusedUserName(enterUsername.getText().trim())) {
						JOptionPane.showMessageDialog(null,"Username already exists.");
					} else if(userList.unusedEmail(enterEmail.getText().trim())) {
						JOptionPane.showMessageDialog(null,"Email already exists.");
					} else {
						BankUser newBankUser = new BankUser(enterUsername.getText().trim(),
															enterFirstName.getText().trim(),
															enterLastName.getText().trim(),
															enterEmail.getText().trim(),
															enterPassword.getText());
						newBankUser.addToList(newBankUser);
						File bankUserPersistance = new File("C:\\Users\\silve\\Desktop\\coding stuff\\brandon_clark_p0\\bankingApplication\\src\\com\\revature\\banking_application\\resources\\data.txt");
						try(FileWriter fileWriter = new FileWriter(bankUserPersistance, true); ){
							System.out.println(newBankUser.toFileString());
							fileWriter.append(newBankUser.toFileString() + "\n");
							fileWriter.flush();
							fileWriter.close();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
						
	            		EmailVerification(userList, enterEmail.getText());
	            		registrationFrame.dispose();
	            	}
	            }
	        });
	        
	        registrationFrame.add(gui);
	        registrationFrame.pack();
	        registrationFrame.setVisible(true);
		}
		
		public void EmailVerification(UserNodes userList, String email) {
			
			final JFrame verificationFrame = new JFrame();
	        verificationFrame.setTitle("Silver Banking");
	        verificationFrame.setSize(500, 500);
	        verificationFrame.setLocation(550, 400);
	        
	        JPanel gui = new JPanel(new GridLayout(0,2,1,10));
	        
	        String title = "Verify Email";
	        Border border = BorderFactory.createTitledBorder(title);
	        gui.setBorder(border);
	        
	        
	        JLabel verificationMessage = new JLabel("Please verify your email at " + email.trim() + ":");
	        gui.add(verificationMessage);
	        
	        Button verify = new Button("Verify");
	        gui.add(verify);
	        verify.addActionListener(new ActionListener() {
				@Override
	            public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null,"Account Confirmed");
					HomePage homeReturn = new HomePage(userList);
					verificationFrame.dispose();
				}
	        });
	        
	        Button returnToRegister = new Button("Return");
	        gui.add(returnToRegister);
	        returnToRegister.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	RegisterPage restartRegister = new RegisterPage(userList);
	            	verificationFrame.dispose();
	            	
	            }
	        });

			
			verificationFrame.add(gui);
	        verificationFrame.pack();
	        verificationFrame.setVisible(true);
		}
		
}
