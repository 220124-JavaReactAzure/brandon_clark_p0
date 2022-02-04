package com.revature.banking_application.inner;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.outer.HomePage;

public class UserPage {
	
	public UserPage(BankUser currentUser) {
		
		final JFrame mainUserFrame = new JFrame();
        mainUserFrame.setTitle("Silver Banking");
        mainUserFrame.setSize(500, 500);
        mainUserFrame.setLocation(550, 400);
        
        JPanel gui = new JPanel(new GridLayout(0,3,1,10));
        
        String title = "Welcome Back " + currentUser.getFirstName();
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        Button viewAccountBalance = new Button("View Account Balances");
        gui.add(viewAccountBalance);
        viewAccountBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new ViewAccountBalancePage(currentUser);
            	mainUserFrame.dispose();
            }
        });
        
        Button viewTransactions = new Button("View Transactions");
        gui.add(viewTransactions);
        viewTransactions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new ViewTransactionsPage();
            	mainUserFrame.dispose();
            }
        });
        
        Button requestJointAccount = new Button("Request Joint Account");
        gui.add(requestJointAccount);
        requestJointAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new RequestJointAccountPage(currentUser);
            	mainUserFrame.dispose();
            }
        });
        
        Button depositFunds = new Button("Deposit Funds");
        gui.add(depositFunds);
        depositFunds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new DepositFundsPage(currentUser);
            	mainUserFrame.dispose();
            }
        });
        
        Button withdrawFunds = new Button("Withdraw Funds");
        gui.add(withdrawFunds);
        withdrawFunds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new WithdrawFundsPage();
            	mainUserFrame.dispose();
            }
        });
        
        Button transferFunds = new Button("Transfer Funds");
        gui.add(transferFunds);
        transferFunds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new TransferFundsPage();
            	mainUserFrame.dispose();
            }
        });
        
        Button createNewAccount = new Button("Create New Account");
        gui.add(createNewAccount);
        createNewAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new CreateNewBankAccountPage(currentUser);
            	mainUserFrame.dispose();
            }
        });
        
        Button userSettings = new Button("User Settings");
        gui.add(userSettings);
        userSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new UserSettingsPage(currentUser);
            	mainUserFrame.dispose();
            }
        });
        
        Button logOut = new Button("Log Out");
        gui.add(logOut);
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new HomePage();
            	mainUserFrame.dispose();
            }
        });
        
        mainUserFrame.add(gui);
        mainUserFrame.pack();
        mainUserFrame.setVisible(true);
	}

}
