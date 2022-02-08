package com.revature.banking_application.util.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Logger {
	private static Logger logger = new Logger(true);
	private final boolean printToConsole;
	private static final String ANSI_CYAN = "\033[0;36m";
	private static final String ANSI_RESET = "\u001B[0m";
	
	private Logger(boolean printToConsole) {
		this.printToConsole = printToConsole;
	}
	
	public static Logger getLogger(boolean printToConsole) {
		if(logger==null) {
			logger = new Logger(printToConsole);
		}
		
		return logger;
	}
	
	public void log(String message) {
		try (Writer logWriter = new FileWriter("src/main/resources/banking_application.log",true);){
			logWriter.write(message + "\n");
			
			if(printToConsole) {
				System.out.println(ANSI_CYAN + message + ANSI_RESET);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
