package com.revatur.banking_application;

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

public class RegisterPage {
		public RegisterPage() {
			
			final JFrame theFrame = new JFrame();
	        theFrame.setTitle("Silver Banking");
	        theFrame.setSize(500, 500);
	        theFrame.setLocation(550, 400);
	        
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
	            	
	            	HomePage hp = new HomePage();
	            	theFrame.dispose();
	            	//JOptionPane.showMessageDialog(null, "register");
	            }
	        });
	        
	        Button submit = new Button("Submit");
	        gui.add(submit);
	        submit.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				@Override
	            public void actionPerformed(ActionEvent e) {
	            	if(enterPassword.getText().equals(enterConfirmPassword.getText())) {
	            		if (enterPassword.getText().trim().isEmpty() || 
	            			enterConfirmPassword.getText().trim().isEmpty() || 
	            			enterUsername.getText().trim().isEmpty() || 
	            			enterLastName.getText().trim().isEmpty() ||
	            			enterFirstName.getText().trim().isEmpty()) {
	            			
	            			JOptionPane.showMessageDialog(null,"Please Fill Out All Information");
	            		}else {
	            			JOptionPane.showMessageDialog(null,"Account Confirmed");
	            			HomePage hp = new HomePage();
	            			theFrame.dispose();
	            		}
	            		
	            	} else {
	            		JOptionPane.showMessageDialog(null,"Please Enter Matching Passwords");
	            	}
	            	
	            	
	            	
	            }
	        });
	        
	        
	        
	        
	        
	        theFrame.add(gui);
	        theFrame.pack();
	        theFrame.setVisible(true);
		}
}
