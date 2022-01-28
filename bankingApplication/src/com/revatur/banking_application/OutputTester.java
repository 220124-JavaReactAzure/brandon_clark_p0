package com.revatur.banking_application;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

public class OutputTester extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTextField field;
    protected JTextArea theText;
	private final static String newline = "\n";

	public OutputTester() //constructor of the DisplayGuiHelp object that has the list passed to it on creation
    {
//		Container contentPane = frame.getContentPane();
//		SpringLayout layout = new SpringLayout();
//		contentPane.setLayout(layout); 
//		
//      field = new JTextField(20);
//      field.addActionListener(this);
        
//      theText = new JTextArea(15,25);
//		
//		contentPane.add(theText);
//      theText.setEditable(false);
        
//		JLabel label = new JLabel("Username: ");
//      JTextField textField = new JTextField("", 15);
//      contentPane.add(label);
//      contentPane.add(textField);

		final JFrame theFrame = new JFrame();
        theFrame.setTitle("Silver Banking");
        theFrame.setSize(500, 500);
        theFrame.setLocation(550, 400);
		
		JPanel gui = new JPanel(new GridLayout(0,2,1,1));
        
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
            	JOptionPane.showMessageDialog(null, "register");
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
        
        
        theFrame.add(gui);
        theFrame.pack();
        theFrame.setVisible(true);
        //JOptionPane.showMessageDialog(null, gui);
        
    }
	
	public static void HomeCall(){
//		final JFrame theFrame = new JFrame();
//        theFrame.setTitle("Silver Banking");
//        theFrame.setSize(500, 500);
//        theFrame.setLocation(550, 400);
//        
        OutputTester oT = new OutputTester();
		
		//theFrame.pack();
        //theFrame.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Click");
		
	}
	

}
