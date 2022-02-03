package com.revature.banking_application;

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

public class HomePage {
	
	public HomePage() {
		
		final JFrame loginFrame = new JFrame();
        loginFrame.setTitle("Silver Banking");
        loginFrame.setSize(500, 500);
        loginFrame.setLocation(550, 400);
		
		JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
        String title = "Welcome to Silver Banking";
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        JLabel username = new JLabel("Username: ");
        gui.add(username);
        
        JTextField enterUsername = new JTextField("", 20);
        enterUsername.setBorder(null);
        gui.add(enterUsername);
        
        JLabel password = new JLabel("Password: ");
        gui.add(password);
        
        JPasswordField enterPassword = new JPasswordField("", 20);
        enterPassword.setBorder(null);
        gui.add(enterPassword);
        
        Button register = new Button("Register");
        gui.add(register);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new RegisterPage();
            	loginFrame.dispose();
            }
        });
        
        Button submit = new Button("Submit");
        gui.add(submit);
        submit.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
			@Override
            public void actionPerformed(ActionEvent e) {
            	if (enterPassword.getText().trim().isEmpty() || 
	            	enterUsername.getText().trim().isEmpty()) {
            		JOptionPane.showMessageDialog(null,"Please fill out all information fields.");
            	} else if(UserVerification.CheckExistingUsername(enterUsername.getText().trim())) {
            		BankUser returningUser =  DatabaseAccess.PullUserFromUsername(enterUsername.getText().trim());
            		if(returningUser.getPassword().equals(enterPassword.getText())) {
            			new UserPage(returningUser);
            			loginFrame.dispose();
            		} else {
                		JOptionPane.showMessageDialog(null,"Username or email is incorrect.");
            		}
            	} else {
            		JOptionPane.showMessageDialog(null,"Username or email is incorrect.");
            	}
            	
            	
            	
            }
        });
        
        Button exit = new Button("Exit"); 
        gui.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	loginFrame.dispose();
            }
        });
        
        Button passReset = new Button("Forgot Password"); 
        gui.add(passReset);
        passReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new PasswordReset();
            	loginFrame.dispose();
            }
        });
        
        loginFrame.add(gui);
        loginFrame.pack();
        loginFrame.setVisible(true);	
		
	}
	

}
