package com.revature.banking_application;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.revature.banking_application.models.BankUser;
import com.revature.banking_application.models.UserNodes;

public class PasswordReset {
	public PasswordReset(UserNodes userList) {
		
		final JFrame verifyUsernameFrame = new JFrame();
        verifyUsernameFrame.setTitle("Silver Banking");
        verifyUsernameFrame.setSize(500, 500);
        verifyUsernameFrame.setLocation(550, 400);
        
        JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
        String title = "Please Enter Username to Reset Password";
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        JLabel username = new JLabel("Username: ");
        gui.add(username);
        
        JTextField enterUsername = new JTextField(null, 20);
        enterUsername.setBorder(null);
        gui.add(enterUsername);
        
        Button returnToHome = new Button("Return");
        gui.add(returnToHome);
        returnToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new HomePage(userList);
            	verifyUsernameFrame.dispose();
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
					if(userList.unusedUserName(enterUsername.getText().trim())) {
						BankUser userToReset = userList.returnBankUser(enterUsername.getText().trim());
						confirmEmail(userList, userToReset);
						verifyUsernameFrame.dispose();
					} else {
						notValidUser(userList);
						verifyUsernameFrame.dispose();
					}
				}	
            }
        });
        
        verifyUsernameFrame.add(gui);
        verifyUsernameFrame.pack();
        verifyUsernameFrame.setVisible(true);
        
        
	}
	
	public void notValidUser(UserNodes userList) {
		final JFrame invalidUserFrame = new JFrame();
        invalidUserFrame.setTitle("Silver Banking");
        invalidUserFrame.setSize(500, 500);
        invalidUserFrame.setLocation(550, 400);
        
        JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
        String title = "Enter Matching Email";
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        JLabel email = new JLabel("Email Address: ");
        gui.add(email);
        
        JTextField enterEmail = new JTextField(null, 20);
        enterEmail.setBorder(null);
        gui.add(enterEmail);
        
        Button returnToPasswordReset = new Button("Return");
        gui.add(returnToPasswordReset);
        returnToPasswordReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new PasswordReset(userList);
            	invalidUserFrame.dispose();
            }
        });
        
        Button submit = new Button("Submit");
        gui.add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (enterEmail.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please fill out all information fields.");
				} else if (!(enterEmail.getText().contains("@"))){
					JOptionPane.showMessageDialog(null,"Please make sure the email address is valid.");
				} else if(!(enterEmail.getText().contains(".com") || enterEmail.getText().contains(".net") || enterEmail.getText().contains(".org"))) {
					JOptionPane.showMessageDialog(null,"Please make sure the email address is valid.");
			    } else {
			    	JOptionPane.showMessageDialog(null,"Username or email is incorrect.");
			    	new PasswordReset(userList);
			    	invalidUserFrame.dispose();
			    }
            }
        });

        invalidUserFrame.add(gui);
        invalidUserFrame.pack();
        invalidUserFrame.setVisible(true);
	}
	
	public void confirmEmail(UserNodes userList, BankUser bankUser) {
		
		final JFrame confirmEmailFrame = new JFrame();
        confirmEmailFrame.setTitle("Silver Banking");
        confirmEmailFrame.setSize(500, 500);
        confirmEmailFrame.setLocation(550, 400);
        
        JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
        String title = "Enter Matching Email";
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        JLabel email = new JLabel("Email Address: ");
        gui.add(email);
        
        JTextField enterEmail = new JTextField(null, 20);
        enterEmail.setBorder(null);
        gui.add(enterEmail);
        
        Button returnToPasswordReset = new Button("Return");
        gui.add(returnToPasswordReset);
        returnToPasswordReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new PasswordReset(userList);
            	confirmEmailFrame.dispose();
            }
        });
        
        Button submit = new Button("Submit");
        gui.add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (enterEmail.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please fill out all information fields.");
				} else if (!(enterEmail.getText().contains("@"))){
					JOptionPane.showMessageDialog(null,"Please make sure the email address is valid.");
				} else if(!(enterEmail.getText().contains(".com") || enterEmail.getText().contains(".net") || enterEmail.getText().contains(".org"))) {
					JOptionPane.showMessageDialog(null,"Please make sure the email address is valid.");
			    } else {
			    	if(bankUser.getEmail().equalsIgnoreCase(enterEmail.getText())) {
			    		enterNewPassword(userList, bankUser);
            			confirmEmailFrame.dispose();
			    	} else {
			    		JOptionPane.showMessageDialog(null,"Username or email is incorrect.");
			    		new PasswordReset(userList);
			    		confirmEmailFrame.dispose();
			    	}
			    }
            	
            }
        });
        
        confirmEmailFrame.add(gui);
        confirmEmailFrame.pack();
        confirmEmailFrame.setVisible(true);
		
	}

	public void enterNewPassword(UserNodes userList, BankUser bankUser) {
		
		final JFrame passwordResetFrame = new JFrame();
        passwordResetFrame.setTitle("Silver Banking");
        passwordResetFrame.setSize(500, 500);
        passwordResetFrame.setLocation(550, 400);
        
        JPanel gui = new JPanel(new GridLayout(0,2,1,10));
        
        String title = "Enter New Password";
        Border border = BorderFactory.createTitledBorder(title);
        gui.setBorder(border);
        
        JLabel password = new JLabel("Password: ");
        gui.add(password);
        
        JPasswordField enterPassword = new JPasswordField(null, 20);
        enterPassword.setBorder(null);
        gui.add(enterPassword);
        
        JLabel confirmPassword = new JLabel("Confirm Password: ");
        gui.add(confirmPassword);
        
        JPasswordField enterConfirmPassword = new JPasswordField(null, 20);
        enterConfirmPassword.setBorder(null);
        gui.add(enterConfirmPassword);
		
        Button returnToPasswordReset = new Button("Return");
        gui.add(returnToPasswordReset);
        returnToPasswordReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	new PasswordReset(userList);
            	passwordResetFrame.dispose();
            }
        });
        
        Button submit = new Button("Submit");
        gui.add(submit);
        submit.addActionListener(new ActionListener() {
        	@SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
        		if (enterPassword.getText().trim().isEmpty() || enterConfirmPassword.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please fill out all information fields.");
				} else if(!(enterPassword.getText().equals(enterConfirmPassword.getText()))) {
					JOptionPane.showMessageDialog(null,"Please enter matching passwords.");
				} else {
					Path dataPath = Paths.get("C:\\Users\\silve\\Desktop\\coding stuff\\brandon_clark_p0\\bankingApplication\\src\\com\\revature\\banking_application\\resources\\data.txt");
					List<String> fileData;
					
					try { 
						fileData = Files.readAllLines(dataPath);
						File bankUserPersistance = new File("C:\\Users\\silve\\Desktop\\coding stuff\\brandon_clark_p0\\bankingApplication\\src\\com\\revature\\banking_application\\resources\\data.txt");
						FileWriter fileErase= new FileWriter(bankUserPersistance, false);
						fileErase.write("");
						fileErase.flush();
						fileErase.close();
						FileWriter fileWriter = new FileWriter(bankUserPersistance, true);
						for(int i=0;i<fileData.size();i++){
							if(fileData.get(i).trim().isEmpty()) {
							//System.out.println("empty line");
							} else {
								if(fileData.get(i).equals(bankUser.toFileString())) {
									bankUser.setPassword(enterPassword.getText());
									fileWriter.append(bankUser.toFileString() + "\n");
								} else {
									fileWriter.append(fileData.get(i) + "\n");
								}
					        }
						} 
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException exception) {
						exception.printStackTrace();
					} finally {
						JOptionPane.showMessageDialog(null,"Password has been reset.");
						new HomePage(userList);
						passwordResetFrame.dispose();
						
					}
				} 
            }
        });

        passwordResetFrame.add(gui);
        passwordResetFrame.pack();
        passwordResetFrame.setVisible(true);
	}

}
