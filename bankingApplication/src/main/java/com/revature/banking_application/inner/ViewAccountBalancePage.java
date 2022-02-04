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
import javax.swing.border.Border;

import com.revature.banking_application.models.AccountNodes;
import com.revature.banking_application.models.BankAccount;
import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.util.DatabaseAccess;

public class ViewAccountBalancePage {
	public ViewAccountBalancePage(BankUser currentUser) {
		if(DatabaseAccess.CheckifUserHasABankAccount(currentUser)) {
			final JFrame viewBalanceFrame = new JFrame();
			viewBalanceFrame.setTitle("Silver Banking");
			viewBalanceFrame.setSize(500, 500);
			viewBalanceFrame.setLocation(550, 400);
		
			JPanel gui = new JPanel(new GridLayout(0,3,25,10));
			String title = "Account Balance";
			Border border = BorderFactory.createTitledBorder(title);
			gui.setBorder(border);
			
			JLabel type = new JLabel("     ACCOUNT TYPE     ");
	        gui.add(type);
			
			JLabel nickname = new JLabel("     ACCOUNT NAME     ");
	        gui.add(nickname);
	        
	        JLabel balanceTitle = new JLabel("     BALANCE     ");
	        gui.add(balanceTitle);
			
			AccountNodes allAccounts = DatabaseAccess.PullBankAccountsFromBankUser(currentUser); 
			
			AccountNodes.Node current = allAccounts.returnHead();
			
			while(current!=null) {
				BankAccount currentAccount = current.getData();
				
				JLabel accountType;
				switch(currentAccount.getUserAccountType()) {
					case 1:
						accountType = new JLabel("Checking Account");
						break;
					case 2:
						accountType = new JLabel("Savings Account");
						break;
					case 3:
						accountType = new JLabel("Joint Checking Account");
						break;
					case 4: 
						accountType = new JLabel("Joint Savings Account");
						break;
					default:
						accountType = new JLabel("This should never happen");
						break;
				}
		        gui.add(accountType);
		        
		        JLabel accountName = new JLabel(currentAccount.getAccountNickname());
		        gui.add(accountName);
		        
		        JLabel balance = new JLabel(String.valueOf(currentAccount.getAccountValue()));
		        gui.add(balance);
		        
		        current = current.getNext();
				
				
			}
			
			JLabel blank = new JLabel("");
	        gui.add(blank);
			
			Button returnToUserSettingsPage = new Button("Return");
			gui.add(returnToUserSettingsPage);
			returnToUserSettingsPage.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
            	
					new UserSettingsPage(currentUser);
					viewBalanceFrame.dispose();
            	
				}
			});	
			
			viewBalanceFrame.add(gui);
			viewBalanceFrame.pack();
			viewBalanceFrame.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null,"A bank account is required. Redirecting...");
			new CreateNewBankAccountPage(currentUser);
		}
		
	
	}

}
