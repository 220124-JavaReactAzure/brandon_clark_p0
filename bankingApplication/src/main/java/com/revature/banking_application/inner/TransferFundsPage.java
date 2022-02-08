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
import com.revature.banking_application.util.logging.Logger;

public class TransferFundsPage {
	
	private static Logger logger;
	
	public TransferFundsPage(BankUser currentUser) {
		logger = Logger.getLogger(true);
		if(DatabaseAccess.CheckifUserHasABankAccount(currentUser)) {
			final JFrame transferFundsFrame = new JFrame();
			transferFundsFrame.setTitle("Silver Banking");
			transferFundsFrame.setSize(500, 500);
			transferFundsFrame.setLocation(550, 400);
		
			JPanel gui = new JPanel(new GridLayout(0,5,25,10));
			String title = "Transfer Funds";
			Border border = BorderFactory.createTitledBorder(title);
			gui.setBorder(border);
			
			JLabel type = new JLabel("     ACCOUNT TYPE     ");
	        gui.add(type);
			
			JLabel nickname = new JLabel("     ACCOUNT NAME     ");
	        gui.add(nickname);
	        
	        JLabel balanceTitle = new JLabel("     BALANCE     ");
	        gui.add(balanceTitle);
	        
	        JLabel depositAmount = new JLabel("     AMOUNT TO TRANSFER     ");
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
		        
		        JTextField amountToTransfer = new JTextField(null, 20);
		        amountToTransfer.setBorder(null);
		        gui.add(amountToTransfer);
		        
		        Button transfer = new Button("Transfer");
				gui.add(transfer);
				transfer.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(accountName.getText().trim().isEmpty() || amountToTransfer.getText().trim().isEmpty()) {
							JOptionPane.showMessageDialog(null,"Please fill out all information fields.");
						}else if(UserVerification.CheckNumeric(amountToTransfer.getText())){
							JOptionPane.showMessageDialog(null,"Transfers must benumeric only.");
						}else if(Double.parseDouble(amountToTransfer.getText()) > currentAccount.getAccountValue()){
							JOptionPane.showMessageDialog(null,"Transfered funds cannot be greater than the funds in your account.");
						} else {
							String transferie = JOptionPane.showInputDialog(null, "Username of Transferie: ");
							int transferieID = UserVerification.CheckExistingUsernameReturnNumber(transferie);
							
							if(transferieID == 0) {
								JOptionPane.showMessageDialog(null,"Account does not exist.");
							} else if (!(DatabaseAccess.CheckifUserHasABankAccount(transferieID))){
								JOptionPane.showMessageDialog(null,"User does not have accounts.");
							} else {
								currentAccount.setAccountValue(currentAccount.getAccountValue() - Double.parseDouble(amountToTransfer.getText()));
								currentAccount.setAccountNickname(accountName.getText().trim());
								Date utilDate = new Date();
								java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
								logger.log("Found User For Transfer");
								DatabaseAccess.UpdateTransactionList(currentAccount.getUserID(), -(Double.parseDouble(amountToTransfer.getText())), sqlDate);
								DatabaseAccess.updateBankAccount(currentAccount);
								TransferToAccount(transferieID, Double.parseDouble(amountToTransfer.getText()), currentUser);
								transferFundsFrame.dispose();
							}
							
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
	            	transferFundsFrame.dispose();
	            }
	        });	
			
			transferFundsFrame.add(gui);
			transferFundsFrame.pack();
			transferFundsFrame.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null,"A bank account is required. Redirecting...");
			new CreateNewBankAccountPage(currentUser);
		}
	}

	public void TransferToAccount(int transferieID, double amountTransfered, BankUser currentUser) {
		logger = Logger.getLogger(true);
		final JFrame transferFundsToAccountFrame = new JFrame();
		transferFundsToAccountFrame.setTitle("Silver Banking");
		transferFundsToAccountFrame.setSize(500, 500);
		transferFundsToAccountFrame.setLocation(550, 400);
	
		JPanel gui2 = new JPanel(new GridLayout(0,4,25,10));
		String title = "Transfer Funds";
		Border border = BorderFactory.createTitledBorder(title);
		gui2.setBorder(border);
		
		JLabel type = new JLabel("     ACCOUNT TYPE     ");
        gui2.add(type);
		
		JLabel nickname = new JLabel("     ACCOUNT NAME     ");
        gui2.add(nickname);
        
        JLabel balanceTitle = new JLabel("     BALANCE     ");
        gui2.add(balanceTitle);

        JLabel blankTitle = new JLabel("     TRANSFER     ");
        gui2.add(blankTitle);
        
        AccountNodes allAccounts2 = DatabaseAccess.PullBankAccountsFromBankUser(transferieID); 
		
		AccountNodes.Node current2 = allAccounts2.returnHead();
		
		while(current2!=null) {
			BankAccount currentAccount2 = current2.getData();
			
			JLabel accountType;
			switch(currentAccount2.getUserAccountType()) {
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
	        gui2.add(accountType);
	        
	        JLabel accountName = new JLabel(currentAccount2.getAccountNickname());
	        gui2.add(accountName);
	        
	        JLabel balance = new JLabel(String.format("%.2f", currentAccount2.getAccountValue()));
	        gui2.add(balance);
	        
	        Button Transfer = new Button("Transfer");
			gui2.add(Transfer);
			Transfer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						currentAccount2.setAccountValue(currentAccount2.getAccountValue() + amountTransfered);
						Date utilDate = new Date();
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
						logger.log("Transfer Complete");
						DatabaseAccess.UpdateTransactionList(currentAccount2.getUserID(), amountTransfered , sqlDate);
						DatabaseAccess.updateBankAccount(currentAccount2);
						JOptionPane.showMessageDialog(null,"Transfer complete.");
						new UserPage(currentUser);
						transferFundsToAccountFrame.dispose();
				}
			});
	        
	        current2 = current2.getNext();
		}
        
		transferFundsToAccountFrame.add(gui2);
		transferFundsToAccountFrame.pack();
		transferFundsToAccountFrame.setVisible(true);
		
	}
}
