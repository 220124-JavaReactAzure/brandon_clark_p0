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

public class PasswordReset {
	
	private static Logger logger;
	
	public PasswordReset() {
		final JFrame verifyUsernameFrame = new JFrame();
        verifyUsernameFrame.setTitle("Silver Banking");
        verifyUsernameFrame.setSize(500, 500);
        verifyUsernameFrame.setLocation(550, 400);
        
        JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
        String title = "Please Enter Username to Reset Password";
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        JLabel username = new JLabel("Username: ");
        gui.add(username);
        
        JTextField enterUsername = new JTextField(null, 20);
        enterUsername.setBorder(null);
        gui.add(enterUsername);
        
        Button returnToHome = new Button("Return");
        gui.add(returnToHome);
        returnToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new HomePage();
            	verifyUsernameFrame.dispose();
            }
        });
        
        Button submit = new Button("Submit");
        gui.add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (enterUsername.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please fill out all information fields.");
				} else {
					if(UserVerification.CheckExistingUsername(enterUsername.getText().trim())) {
						BankUser userToReset = DatabaseAccess.PullUserFromUsername(enterUsername.getText().trim());
						confirmEmail(userToReset);
						verifyUsernameFrame.dispose();
					} else {
						notValidUser();
						verifyUsernameFrame.dispose();
					}
				}	
            }
        });
        
        verifyUsernameFrame.add(gui);
        verifyUsernameFrame.pack();
        verifyUsernameFrame.setVisible(true);
        
        
	}
	
	public void notValidUser() {
		logger = Logger.getLogger(true);
		
		final JFrame invalidUserFrame = new JFrame();
        invalidUserFrame.setTitle("Silver Banking");
        invalidUserFrame.setSize(500, 500);
        invalidUserFrame.setLocation(550, 400);
        
        JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
        String title = "Enter Matching Email";
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        JLabel email = new JLabel("Email Address: ");
        gui.add(email);
        
        JTextField enterEmail = new JTextField(null, 20);
        enterEmail.setBorder(null);
        gui.add(enterEmail);
        
        Button returnToPasswordReset = new Button("Return");
        gui.add(returnToPasswordReset);
        returnToPasswordReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new PasswordReset();
            	invalidUserFrame.dispose();
            }
        });
        
        Button submit = new Button("Submit");
        gui.add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (enterEmail.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please fill out all information fields.");
				} else if (UserVerification.CheckEmailContains(enterEmail.getText())){
					JOptionPane.showMessageDialog(null,"Please make sure the email address is valid.");
				} else {
			    	JOptionPane.showMessageDialog(null,"Username or email is incorrect.");
			    	logger.log("Incorrect Username");
			    	new PasswordReset();
			    	invalidUserFrame.dispose();
			    }
            }
        });

        invalidUserFrame.add(gui);
        invalidUserFrame.pack();
        invalidUserFrame.setVisible(true);
	}
	
	public void confirmEmail(BankUser bankUser) {
		logger = Logger.getLogger(true);
		
		final JFrame confirmEmailFrame = new JFrame();
        confirmEmailFrame.setTitle("Silver Banking");
        confirmEmailFrame.setSize(500, 500);
        confirmEmailFrame.setLocation(550, 400);
        
        JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
        String title = "Enter Matching Email";
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        JLabel email = new JLabel("Email Address: ");
        gui.add(email);
        
        JTextField enterEmail = new JTextField(null, 20);
        enterEmail.setBorder(null);
        gui.add(enterEmail);
        
        Button returnToPasswordReset = new Button("Return");
        gui.add(returnToPasswordReset);
        returnToPasswordReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new PasswordReset();
            	confirmEmailFrame.dispose();
            }
        });
        
        Button submit = new Button("Submit");
        gui.add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (enterEmail.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please fill out all information fields.");
				} else if (UserVerification.CheckEmailContains(enterEmail.getText())){
					JOptionPane.showMessageDialog(null,"Please make sure the email address is valid.");
				} else {
			    	if(bankUser.getEmail().equalsIgnoreCase(enterEmail.getText())) {
			    		enterNewPassword(bankUser);
            			confirmEmailFrame.dispose();
			    	} else {
			    		JOptionPane.showMessageDialog(null,"Username or email is incorrect.");
			    		logger.log("Incorrect Email");
			    		new PasswordReset();
			    		confirmEmailFrame.dispose();
			    	}
			    }
            	
            }
        });
        
        confirmEmailFrame.add(gui);
        confirmEmailFrame.pack();
        confirmEmailFrame.setVisible(true);
		
	}

	public void enterNewPassword(BankUser bankUser) {
		logger = Logger.getLogger(true);
		
		final JFrame passwordResetFrame = new JFrame();
        passwordResetFrame.setTitle("Silver Banking");
        passwordResetFrame.setSize(500, 500);
        passwordResetFrame.setLocation(550, 400);
        
        JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
        String title = "Enter New Password";
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
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
		
        Button returnToPasswordReset = new Button("Return");
        gui.add(returnToPasswordReset);
        returnToPasswordReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new PasswordReset();
            	passwordResetFrame.dispose();
            }
        });
        
        Button submit = new Button("Submit");
        gui.add(submit);
        submit.addActionListener(new ActionListener() {
        	@SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
        		if (enterPassword.getText().trim().isEmpty() || enterConfirmPassword.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please fill out all information fields.");
				} else if(!(enterPassword.getText().equals(enterConfirmPassword.getText()))) {
					JOptionPane.showMessageDialog(null,"Please enter matching passwords.");
				} else {
					bankUser.setPassword(enterPassword.getText());
					DatabaseAccess.ChangePassword(bankUser);
					logger.log(bankUser.getFirstName()+ " " + bankUser.getLastName() + "'s Password Has Been Changed");				
					JOptionPane.showMessageDialog(null,"Password has been reset.");
					new HomePage();
					passwordResetFrame.dispose();
				} 
            }
        });

        passwordResetFrame.add(gui);
        passwordResetFrame.pack();
        passwordResetFrame.setVisible(true);
	}

}
