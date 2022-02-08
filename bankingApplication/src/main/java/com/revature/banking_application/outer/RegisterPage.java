package com.revature.banking_application.outer;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.util.DatabaseAccess;
import com.revature.banking_application.util.UserVerification;
import com.revature.banking_application.util.logging.Logger;

public class RegisterPage {
	
	private static Logger logger;
	
		public RegisterPage() {
			logger = Logger.getLogger(true);
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
	            	
	            	new HomePage();
	            	registrationFrame.dispose();
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
					} 
					
					else if(UserVerification.CheckCapitalization(enterFirstName.getText())) {
						JOptionPane.showMessageDialog(null,"Please have the first letter of your first name capitalized.");
					} 
					
					else if (UserVerification.CheckCapitalization(enterLastName.getText())){
						JOptionPane.showMessageDialog(null,"Please have the first letter of your last name capitalized.");
					} 
					
					else if (UserVerification.CheckEmailContains(enterEmail.getText())){
						JOptionPane.showMessageDialog(null,"Please make sure the email address is valid.");
					} 
					
					else if(!(enterPassword.getText().equals(enterConfirmPassword.getText()))) {
						JOptionPane.showMessageDialog(null,"Please enter matching passwords.");
					} 
					
					else if(UserVerification.CheckExistingUsername(enterUsername.getText().trim())) {
						JOptionPane.showMessageDialog(null,"Username already exists.");
					} 
					
					else if(UserVerification.CheckExistingEmail(enterEmail.getText().trim())) {
						JOptionPane.showMessageDialog(null,"Email already exists.");
					} 
					
					else {
						BankUser newBankUser = new BankUser(enterUsername.getText().trim(),
															enterFirstName.getText().trim(),
															enterLastName.getText().trim(),
															enterEmail.getText().trim(),
															enterPassword.getText());
						DatabaseAccess.CreateUser(newBankUser);
						logger.log("Account Created");
	            		EmailVerification(enterEmail.getText());
	            		registrationFrame.dispose();
	            	}
	            }
	        });
	        
	        registrationFrame.add(gui);
	        registrationFrame.pack();
	        registrationFrame.setVisible(true);
		}
		
		public void EmailVerification(String email) {
			
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
					new HomePage();
					verificationFrame.dispose();
				}
	        });
	        
	        Button returnToRegister = new Button("Return");
	        gui.add(returnToRegister);
	        returnToRegister.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	new RegisterPage();
	            	verificationFrame.dispose();
	            	
	            }
	        });

			
			verificationFrame.add(gui);
	        verificationFrame.pack();
	        verificationFrame.setVisible(true);
		}
		
}
