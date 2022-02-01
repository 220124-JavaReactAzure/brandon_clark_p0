package com.revature.banking_application;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.models.UserNodes;

public class UserPage {
	
	public UserPage(UserNodes userList, BankUser currentUser) {
		
		final JFrame mainUserFrame = new JFrame();
        mainUserFrame.setTitle("Silver Banking");
        mainUserFrame.setSize(500, 500);
        mainUserFrame.setLocation(550, 400);
        
        JPanel gui = new JPanel(new GridLayout(0,3,1,10));
        
        String title = "Welcome Back " + currentUser.getFirstName();
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        Button button1 = new Button("Button1");
        gui.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new HomePage(userList);
            	mainUserFrame.dispose();
            }
        });
        
        Button button2 = new Button("Button2");
        gui.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new HomePage(userList);
            	mainUserFrame.dispose();
            }
        });
        
        Button button3 = new Button("Button3");
        gui.add(button3);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new HomePage(userList);
            	mainUserFrame.dispose();
            }
        });
        
        Button button4 = new Button("Button4");
        gui.add(button4);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new HomePage(userList);
            	mainUserFrame.dispose();
            }
        });
        
        Button button5 = new Button("Button5");
        gui.add(button5);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new HomePage(userList);
            	mainUserFrame.dispose();
            }
        });
        
        Button button6 = new Button("Button6");
        gui.add(button6);
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new HomePage(userList);
            	mainUserFrame.dispose();
            }
        });
        
        Button button7 = new Button("Button7");
        gui.add(button7);
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new HomePage(userList);
            	mainUserFrame.dispose();
            }
        });
        
        Button userSettings = new Button("User Settings");
        gui.add(userSettings);
        userSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new HomePage(userList);
            	mainUserFrame.dispose();
            }
        });
        
        Button logOut = new Button("Log Out");
        gui.add(logOut);
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new HomePage(userList);
            	mainUserFrame.dispose();
            }
        });
        
        mainUserFrame.add(gui);
        mainUserFrame.pack();
        mainUserFrame.setVisible(true);
	}

}
