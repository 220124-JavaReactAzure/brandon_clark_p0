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

import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.models.Transaction;
import com.revature.banking_application.models.TransactionNodes;
import com.revature.banking_application.util.DatabaseAccess;

public class ViewTransactionsPage {
	 public ViewTransactionsPage(BankUser currentUser) {
		 if(DatabaseAccess.CheckifUserHasABankAccount(currentUser)) {
				final JFrame viewBalanceFrame = new JFrame();
				viewBalanceFrame.setTitle("Silver Banking");
				viewBalanceFrame.setSize(500, 500);
				viewBalanceFrame.setLocation(550, 400);
				int count = 1;
			
				JPanel gui = new JPanel(new GridLayout(0,3,25,10));
				String title = "Transaction History";
				Border border = BorderFactory.createTitledBorder(title);
				gui.setBorder(border);
				
				JLabel type = new JLabel("     TRANSACTION NUMBER     ");
		        gui.add(type);
				
				JLabel nickname = new JLabel("     TRANSACTION VALUE     ");
		        gui.add(nickname);
		        
		        JLabel balanceTitle = new JLabel("     DATE    ");
		        gui.add(balanceTitle);
				
				TransactionNodes allTransactions = DatabaseAccess.ReturnAllTransactions(currentUser); 
				
				TransactionNodes.Node current = allTransactions.returnHead();
				
				while(current!=null) {
					Transaction currentTransaction = current.getData();
					
					JLabel transactionCounter = new JLabel(String.valueOf(count));
			        gui.add(transactionCounter);
			        
			        JLabel transactionValue = new JLabel(String.valueOf(currentTransaction.getTransactionValue()));
			        gui.add(transactionValue);
			        
			        JLabel transactionDate = new JLabel(String.valueOf(currentTransaction.getDate()));
			        gui.add(transactionDate);
			        
			        current = current.getNext();
			        count++;
					
					
				}
				
				JLabel blank = new JLabel("");
		        gui.add(blank);
				
		        Button returnToUserPage = new Button("Return");
		        gui.add(returnToUserPage);
		        returnToUserPage.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	new UserPage(currentUser);
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
