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
import javax.swing.border.Border;

import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.util.DatabaseAccess;

public class UserSettingsPage {
	public UserSettingsPage(BankUser currentUser) {
		
		final JFrame userSettingsFrame = new JFrame();
        userSettingsFrame.setTitle("Silver Banking");
        userSettingsFrame.setSize(500, 500);
        userSettingsFrame.setLocation(550, 400);
		
		JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
        String title = currentUser.getFirstName() + "'s settings";
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        Button changePassword = new Button("Change Password");
        gui.add(changePassword);
        changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ChangePasswordPage(currentUser);
            	userSettingsFrame.dispose();
            }
        });
        
        Button returnToUserPage = new Button("Return");
        gui.add(returnToUserPage);
        returnToUserPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new UserPage(currentUser);
            	userSettingsFrame.dispose();
            }
        });

        userSettingsFrame.add(gui);
        userSettingsFrame.pack();
        userSettingsFrame.setVisible(true);	
	}
	
	public void ChangePasswordPage(BankUser currentUser) {
		final JFrame passwordChangeFrame = new JFrame();
        passwordChangeFrame.setTitle("Silver Banking");
        passwordChangeFrame.setSize(500, 500);
        passwordChangeFrame.setLocation(550, 400);
		
		JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
        String title = "Change Password";
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        JLabel currentPassword = new JLabel("Current Password: ");
        gui.add(currentPassword);
        
        JPasswordField enterCurrentPassword = new JPasswordField(null, 20);
        enterCurrentPassword.setBorder(null);
        gui.add(enterCurrentPassword);
        
        JLabel newPassword = new JLabel("New Password: ");
        gui.add(newPassword);
        
        JPasswordField enterNewPassword = new JPasswordField(null, 20);
        enterNewPassword.setBorder(null);
        gui.add(enterNewPassword);
        
        JLabel confirmNewPassword = new JLabel("Confirm New Password: ");
        gui.add(confirmNewPassword);
        
        JPasswordField enterConfirmNewPassword = new JPasswordField(null, 20);
        enterConfirmNewPassword.setBorder(null);
        gui.add(enterConfirmNewPassword);
        
        Button returnToUserSettingsPage = new Button("Return");
        gui.add(returnToUserSettingsPage);
        returnToUserSettingsPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new UserSettingsPage(currentUser);
            	passwordChangeFrame.dispose();
            }
        });
        
        Button submit = new Button("Submit");
        gui.add(submit);
        submit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
            public void actionPerformed(ActionEvent e) {
				if (enterCurrentPassword.getText().trim().isEmpty() || 
            		enterConfirmNewPassword.getText().trim().isEmpty() || 
            		enterNewPassword.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please fill out all information fields.");
				} 
				
				else if(!(enterCurrentPassword.getText().equals(currentUser.getPassword()))) {
					JOptionPane.showMessageDialog(null,"Incorrect password.");
				}
				
				else if(!(enterNewPassword.getText().equals(enterConfirmNewPassword.getText()))) {
					JOptionPane.showMessageDialog(null,"Please make sure new passwords match.");
				} else {
					currentUser.setPassword(enterNewPassword.getText());
					DatabaseAccess.ChangePassword(currentUser);
									
					JOptionPane.showMessageDialog(null,"Password has been changed.");
					new UserSettingsPage(currentUser);
					passwordChangeFrame.dispose();
	            }
			}
        });
        
        passwordChangeFrame.add(gui);
        passwordChangeFrame.pack();
        passwordChangeFrame.setVisible(true);	
	}
}
