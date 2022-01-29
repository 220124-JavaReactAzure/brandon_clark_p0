package com.revatur.banking_application;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.revatur.banking_application.BankDriver;
import com.revatur.banking_application.OutputTester;

public class HomePage {
	
	//static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
	
	public HomePage() {
		
		//System.out.println("Welcome to Silver Banking");
		//OutputTester.HomeCall();
		final JFrame theFrame = new JFrame();
        theFrame.setTitle("Silver Banking");
        theFrame.setSize(500, 500);
        theFrame.setLocation(550, 400);
		
		JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
        String title = "Welcome to Silver Banking";
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        JLabel username = new JLabel("Username: ");
        gui.add(username);
        
        JTextField enterName = new JTextField("", 20);
        enterName.setBorder(null);
        gui.add(enterName);
        
        JLabel password = new JLabel("Password: ");
        gui.add(password);
        
        JPasswordField enterPassword = new JPasswordField("", 20);
        enterName.setBorder(null);
        gui.add(enterPassword);
        enterPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	theFrame.dispose();
            }
        });
        
        Button register = new Button("Register");
        gui.add(register);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	RegisterPage newUser = new RegisterPage();
            	theFrame.dispose();
            	//JOptionPane.showMessageDialog(null, "register");
            }
        });
        
        Button passReset = new Button("Forgot Password"); 
        gui.add(passReset);
        passReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, "reset");
            }
        });
        
        Button exit = new Button("Exit"); 
        gui.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	theFrame.dispose();
            }
        });
        
        
        theFrame.add(gui);
        theFrame.pack();
        theFrame.setVisible(true);
		
		
		
	}
	

}
