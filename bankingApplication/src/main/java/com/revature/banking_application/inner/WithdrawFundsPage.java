package com.revature.banking_application.inner;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.revature.banking_application.models.AccountNodes;
import com.revature.banking_application.models.BankAccount;
import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.util.DatabaseAccess;
import com.revature.banking_application.util.UserVerification;

public class WithdrawFundsPage {
	public WithdrawFundsPage(BankUser currentUser) {
		if(DatabaseAccess.CheckifUserHasABankAccount(currentUser)) {
			final JFrame withdrawFundsFrame = new JFrame();
			withdrawFundsFrame.setTitle("Silver Banking");
			withdrawFundsFrame.setSize(500, 500);
			withdrawFundsFrame.setLocation(550, 400);
		
			JPanel gui = new JPanel(new GridLayout(0,5,25,10));
			String title = "Withdraw Funds";
			Border border = BorderFactory.createTitledBorder(title);
			gui.setBorder(border);
			
			JLabel type = new JLabel("     ACCOUNT TYPE     ");
	        gui.add(type);
			
			JLabel nickname = new JLabel("     ACCOUNT NAME     ");
	        gui.add(nickname);
	        
	        JLabel balanceTitle = new JLabel("     BALANCE     ");
	        gui.add(balanceTitle);
	        
	        JLabel depositAmount = new JLabel("     AMOUNT TO WITHDRAW     ");
	        gui.add(depositAmount);
	        
	        JLabel blankTitle = new JLabel("");
	        gui.add(blankTitle);
			
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
		        
		        JTextField accountName = new JTextField(currentAccount.getAccountNickname(), 20);
		        accountName.setBorder(null);
		        gui.add(accountName);
		        
		        JLabel balance = new JLabel(String.format("%.2f", currentAccount.getAccountValue()));
		        gui.add(balance);
		        
		        JTextField amountToWithdraw = new JTextField(null, 20);
		        amountToWithdraw.setBorder(null);
		        gui.add(amountToWithdraw);
		        
		        Button withdraw = new Button("Withdraw");
				gui.add(withdraw);
				withdraw.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(accountName.getText().trim().isEmpty() || amountToWithdraw.getText().trim().isEmpty()) {
							JOptionPane.showMessageDialog(null,"Please fill out all information fields.");
						}else if(UserVerification.CheckNumeric(amountToWithdraw.getText())){
							JOptionPane.showMessageDialog(null,"Withdraws must benumeric only.");
						}else if(Double.parseDouble(amountToWithdraw.getText()) > currentAccount.getAccountValue()){
							JOptionPane.showMessageDialog(null,"Withdraws cannot be greater than the funds in your account.");
						} else {
							currentAccount.setAccountValue(currentAccount.getAccountValue() - Double.parseDouble(amountToWithdraw.getText()));
							currentAccount.setAccountNickname(accountName.getText().trim());
							Date utilDate = new Date();
							java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
							DatabaseAccess.UpdateTransactionList(currentAccount.getUserID(), -(Double.parseDouble(amountToWithdraw.getText())), sqlDate);
							DatabaseAccess.updateBankAccount(currentAccount);
							JOptionPane.showMessageDialog(null,"Withdraw complete.");
							new WithdrawFundsPage(currentUser);
							withdrawFundsFrame.dispose();
						}
						
	            	
					}
				});
		        
		        current = current.getNext();
				
				
			}
			
			JLabel blank = new JLabel("");
	        gui.add(blank);
	        
	        JLabel blank2 = new JLabel("");
	        gui.add(blank2);
			
	        Button returnToUserPage = new Button("Return");
	        gui.add(returnToUserPage);
	        returnToUserPage.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	new UserPage(currentUser);
	            	withdrawFundsFrame.dispose();
	            }
	        });	
			
			withdrawFundsFrame.add(gui);
			withdrawFundsFrame.pack();
			withdrawFundsFrame.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null,"A bank account is required. Redirecting...");
			new CreateNewBankAccountPage(currentUser);
		}
	}

}
