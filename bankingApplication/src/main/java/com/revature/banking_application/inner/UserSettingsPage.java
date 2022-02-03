package com.revature.banking_application.inner;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
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
        
        Button changeUserInfo = new Button("Change User Information");
        gui.add(changeUserInfo);
        changeUserInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ChangeUserInfoPage(currentUser);
            	userSettingsFrame.dispose();
            }
        });
        
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
	
	public void ChangeUserInfoPage(BankUser currentUser) {
		String[] stateComboBox = {
				"  ", "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC",  
			    "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA",  
			    "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE",  
			    "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC",  
			    "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"
	};
		int currentState = 0;
		
		final JFrame userInfoChangeFrame = new JFrame();
        userInfoChangeFrame.setTitle("Silver Banking");
        userInfoChangeFrame.setSize(500, 500);
        userInfoChangeFrame.setLocation(550, 400);
		
		JPanel gui = new JPanel(new GridLayout(0,2,1,10));
		JPanel verifySocial = new JPanel(new GridLayout(0,2,1,10));
		
		String socialTitle = "Verfiy New Social Security Number";
        Border socialBorder = BorderFactory.createTitledBorder(socialTitle);
        verifySocial.setBorder(socialBorder);
        
        JLabel socialVerifySocial = new JLabel("Verfiy Social: ");
        verifySocial.add(socialVerifySocial);
        
        JTextField enterVerifySocial = new JPasswordField(null, 20);
        enterVerifySocial.setBorder(null);
        verifySocial.add(enterVerifySocial);
		
        
        String title = "Change User Information";
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        JLabel firstName = new JLabel("*First Name: ");
        gui.add(firstName);
        
        JTextField enterFirstName = new JTextField(currentUser.getFirstName(), 20);
        enterFirstName.setBorder(null);
        gui.add(enterFirstName);
        
        JLabel lastName = new JLabel("*Last Name: ");
        gui.add(lastName);
        
        JTextField enterLastName = new JTextField(currentUser.getLastName(), 20);
        enterLastName.setBorder(null);
        gui.add(enterLastName);
        
        JLabel email = new JLabel("*Email Address: ");
        gui.add(email);
        
        JTextField enterEmail = new JTextField(currentUser.getEmail(), 20);
        enterEmail.setBorder(null);
        gui.add(enterEmail);
        
        JLabel social = new JLabel("Social Security: ");
        gui.add(social);
        
        JTextField enterSocial = new JPasswordField(currentUser.getSocial(), 20);
        enterSocial.setBorder(null);
        gui.add(enterSocial);
        
        JLabel address = new JLabel("Address: ");
        gui.add(address);
        
        JTextField enterAddress = new JTextField(currentUser.getAddress(), 20);
        enterAddress.setBorder(null);
        gui.add(enterAddress);  
        
        JLabel city = new JLabel("City: ");
        gui.add(city);
        
        JTextField enterCity = new JTextField(currentUser.getCity(), 20);
        enterCity.setBorder(null);
        gui.add(enterCity); 
        
        JLabel state = new JLabel("State: ");
        gui.add(state);
        
        JComboBox<String> stateBox = new JComboBox<>(stateComboBox);
        stateBox.setBounds(80, 50, 20, 20);
        stateBox.setBackground(Color.WHITE);
        
        if(currentUser.getState().equals("")) {
        	currentState = 0;
        } else {
        	for(int i = 0; i < stateComboBox.length; i++) {
        		if(currentUser.getState().equals(stateComboBox[i])) {
        			currentState = i;
        		}
        	}
        }	
        stateBox.setSelectedIndex(currentState);
        gui.add(stateBox);
        
        JLabel zip = new JLabel("Zip Code: ");
        gui.add(zip);
        
        JTextField enterZip = new JTextField(currentUser.getZipCode(), 20);
        enterZip.setBorder(null);
        gui.add(enterZip);
        Button returnToUserSettingsPage = new Button("Return");
        gui.add(returnToUserSettingsPage);
        returnToUserSettingsPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
				new UserSettingsPage(currentUser);
            	userInfoChangeFrame.dispose();
            	
            }
        });
        
        Button submit = new Button("Submit");
        gui.add(submit);
        submit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
            public void actionPerformed(ActionEvent e) {
				if (enterLastName.getText().trim().isEmpty() ||
            		enterFirstName.getText().trim().isEmpty() ||
            		enterEmail.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"First Name, Last Name, and Email are required fields.");
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
				
				else if(!(currentUser.getSocial().trim().equals(enterSocial.getText().trim())) && !(enterSocial.getText().length() == 9)) {
					JOptionPane.showMessageDialog(null,"Social security numbers must be 9 numbers.");
				} 
				
				else if(!(currentUser.getZipCode().trim().equals(enterZip.getText().trim())) && !(enterZip.getText().length() == 5)) {
					JOptionPane.showMessageDialog(null,"Zip Codes must be 5 numbers.");
				} 
				
				else if(UserVerification.CheckNumeric(enterSocial.getText())) {
					JOptionPane.showMessageDialog(null,"Social security must benumeric only.");
				}
				
				else if(UserVerification.CheckNumeric(enterZip.getText())){
					JOptionPane.showMessageDialog(null,"Zip code must be numeric only.");
				}
				
				else if(UserVerification.CheckExistingEmail(enterEmail.getText().trim(), currentUser)) {
					JOptionPane.showMessageDialog(null,"Email belongs to another user.");
				} 
				
				else {
					if(!(currentUser.getSocial().equals(enterSocial.getText()))) {
						JOptionPane.showMessageDialog(null, verifySocial);
						if(enterVerifySocial.getText().equals(enterSocial.getText())) {
							currentUser.setFirstName(enterFirstName.getText().trim());
							currentUser.setLastName(enterLastName.getText().trim());
							currentUser.setEmail(enterEmail.getText().trim());
							currentUser.setSocial(enterSocial.getText());
							currentUser.setAddress(enterAddress.getText());
							currentUser.setCity(enterCity.getText());
							currentUser.setState(stateBox.getItemAt(stateBox.getSelectedIndex()));
							currentUser.setZipCode(enterZip.getText());
							
							DatabaseAccess.UpdateUser(currentUser);
							JOptionPane.showMessageDialog(null,"Account information updated.");
							new UserSettingsPage(currentUser);
			            	userInfoChangeFrame.dispose();
						}else {
							JOptionPane.showMessageDialog(null,"Social security numbers do not match.");
						}
					} else {
						currentUser.setFirstName(enterFirstName.getText().trim());
						currentUser.setLastName(enterLastName.getText().trim());
						currentUser.setEmail(enterEmail.getText().trim());
						currentUser.setSocial(enterSocial.getText());
						currentUser.setAddress(enterAddress.getText());
						currentUser.setCity(enterCity.getText());
						currentUser.setState(stateBox.getItemAt(stateBox.getSelectedIndex()));
						currentUser.setZipCode(enterZip.getText());
					
						DatabaseAccess.UpdateUser(currentUser);
						JOptionPane.showMessageDialog(null,"Account information updated.");
						new UserSettingsPage(currentUser);
		            	userInfoChangeFrame.dispose();
					}
					
            	}
            }
        });
        
        
        userInfoChangeFrame.add(gui);
        userInfoChangeFrame.pack();
        userInfoChangeFrame.setVisible(true);
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
