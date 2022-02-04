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
import javax.swing.border.Border;

import com.revature.banking_application.models.BankAccount;
import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.util.DatabaseAccess;
import com.revature.banking_application.util.UserVerification;

public class CreateNewBankAccountPage {
	public CreateNewBankAccountPage(BankUser currentUser){
		if(UserVerification.CheckAllValuesAreNotNull(currentUser)) {
			JOptionPane.showMessageDialog(null,"Additional user information required for bank account creation. Redirecting...");
			UserSettingsPage.ChangeUserInfoPage(currentUser);
		}else {
			String[] singleAccounts = {"", "Checking Account", "Savings Account"};
			String[] jointAccounts = {"", "Checking Account", "Savings Account", "Joint Checking Account", "Joint Savings Account"};
 			if(currentUser.getJointUserID() == 0) {
 				final JFrame createNewBankAccountFrame = new JFrame();
 				createNewBankAccountFrame.setTitle("Silver Banking");
				createNewBankAccountFrame.setSize(500, 500);
				createNewBankAccountFrame.setLocation(550, 400);
        
				JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
				String title = "Create New Bank Account";
				Border border = BorderFactory.createTitledBorder(title);
				gui.setBorder(border);
			
				JLabel accountType = new JLabel("Choose account type: ");
	        	gui.add(accountType);
	        
	        	JComboBox<String> singleAccountBox = new JComboBox<>(singleAccounts);
	        	singleAccountBox.setBounds(80, 50, 20, 20);
	        	singleAccountBox.setBackground(Color.WHITE);
	        	gui.add(singleAccountBox);
	        
	        	Button returnToUserSettingsPage = new Button("Return");
	        	gui.add(returnToUserSettingsPage);
	        	returnToUserSettingsPage.addActionListener(new ActionListener() {
	            	@Override
	            	public void actionPerformed(ActionEvent e) {
	            	
						new UserPage(currentUser);
						createNewBankAccountFrame.dispose();
	            	
	            	}
	        	});
	        
	        	Button submit = new Button("Submit");
	        	gui.add(submit);
	        	submit.addActionListener(new ActionListener() {
					@Override
	            	public void actionPerformed(ActionEvent e) {
	            		if(!(singleAccountBox.getItemAt(singleAccountBox.getSelectedIndex()).equals(""))) {
	            			BankAccount newBankAccount = new BankAccount(currentUser.getUserID(), singleAccountBox.getSelectedIndex(), singleAccountBox.getItemAt(singleAccountBox.getSelectedIndex()), 0.00);
	            			DatabaseAccess.CreateBankAccount(newBankAccount);
	            			JOptionPane.showMessageDialog(null,"Account created.");
	            			new UserPage(currentUser);
							createNewBankAccountFrame.dispose();
	            		}else {
	            			JOptionPane.showMessageDialog(null,"Please select account type you wish to create.");
	            		}
	            	}
	        	});
	        	createNewBankAccountFrame.add(gui);
				createNewBankAccountFrame.pack();
				createNewBankAccountFrame.setVisible(true);	
 			}else{
 				final JFrame createNewBankAccountFrame = new JFrame();
 				createNewBankAccountFrame.setTitle("Silver Banking");
				createNewBankAccountFrame.setSize(500, 500);
				createNewBankAccountFrame.setLocation(550, 400);
        
				JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
				String title = "Create New Bank Account";
				Border border = BorderFactory.createTitledBorder(title);
				gui.setBorder(border);
			
				JLabel accountType = new JLabel("Choose account type: ");
	        	gui.add(accountType);
	        
	        	JComboBox<String> jointAccountBox = new JComboBox<>(jointAccounts);
	        	jointAccountBox.setBounds(80, 50, 20, 20);
	        	jointAccountBox.setBackground(Color.WHITE);
	        	gui.add(jointAccountBox);
	        
	        	Button returnToUserSettingsPage = new Button("Return");
	        	gui.add(returnToUserSettingsPage);
	        	returnToUserSettingsPage.addActionListener(new ActionListener() {
	            	@Override
	            	public void actionPerformed(ActionEvent e) {
	            	
						new UserPage(currentUser);
						createNewBankAccountFrame.dispose();
	            	
	            	}
	        	});
	        
	        	Button submit = new Button("Submit");
	        	gui.add(submit);
	        	submit.addActionListener(new ActionListener() {
					@Override
	            	public void actionPerformed(ActionEvent e) {
	            		if(!(jointAccountBox.getItemAt(jointAccountBox.getSelectedIndex()).equals(""))) {
	            			BankAccount newBankAccount = new BankAccount(currentUser.getUserID(), currentUser.getJointUserID(), jointAccountBox.getSelectedIndex(), jointAccountBox.getItemAt(jointAccountBox.getSelectedIndex()), 0.00);
	            			DatabaseAccess.CreateJointBankAccount(newBankAccount);
	            			JOptionPane.showMessageDialog(null,"Account created.");
	            			new UserPage(currentUser);
							createNewBankAccountFrame.dispose();
	            		}else {
	            			JOptionPane.showMessageDialog(null,"Please select account type you wish to create.");
	            		}
	            	}
	        	});
	        	createNewBankAccountFrame.add(gui);
				createNewBankAccountFrame.pack();
				createNewBankAccountFrame.setVisible(true);	
 			}
 			
		}	
	}
		
}
