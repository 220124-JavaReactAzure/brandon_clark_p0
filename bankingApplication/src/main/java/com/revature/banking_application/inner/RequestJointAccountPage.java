package com.revature.banking_application.inner;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.util.DatabaseAccess;
import com.revature.banking_application.util.UserVerification;

public class RequestJointAccountPage {
	public RequestJointAccountPage(BankUser currentUser) {
		if(currentUser.getJointUserID() != 0) {
			JOptionPane.showMessageDialog(null,"You cannot have multiple joint users yet. Sorry for the inconvenience. Redirecting...");
			new UserPage(currentUser);
		} else if(UserVerification.CheckAllValuesAreNotNull(currentUser)){
			JOptionPane.showMessageDialog(null,"Additional user information required for bank account creation. Redirecting...");
			UserSettingsPage.ChangeUserInfoPage(currentUser);
		} else {
			final JFrame jointAccountFrame = new JFrame();
			jointAccountFrame.setTitle("Silver Banking");
			jointAccountFrame.setSize(500, 500);
			jointAccountFrame.setLocation(550, 400);
        
			JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
			String title = "Request Joint Account";
			Border border = BorderFactory.createTitledBorder(title);
			gui.setBorder(border);
        
			JLabel username = new JLabel("Username of Jointie: ");
			gui.add(username);
        
			JTextField enterUsername = new JTextField(null, 20);
			enterUsername.setBorder(null);
			gui.add(enterUsername);
        
			Button returnToUserPage = new Button("Return");
	        gui.add(returnToUserPage);
	        returnToUserPage.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	new UserPage(currentUser);
	            	jointAccountFrame.dispose();
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
						int jointID = UserVerification.CheckExistingUsername(enterUsername.getText().trim(), currentUser);
						if(jointID == 0) {
							JOptionPane.showMessageDialog(null,"Account does not exist.");
						} else {
							currentUser.setJointUserID(jointID);
							DatabaseAccess.updateJointAccounts(currentUser);
							JOptionPane.showMessageDialog(null,"Joint account created.");
							new UserPage(currentUser);
							jointAccountFrame.dispose();
						}
					}	
				}
			});
        
			jointAccountFrame.add(gui);
        	jointAccountFrame.pack();
        	jointAccountFrame.setVisible(true);
		}
		
	}
}
