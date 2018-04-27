package com.revature.project_0;

import static com.revature.scanner.BankScanner.*;
import static com.revature.logger.BankLogger.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class OnClose {
	
	public static void programHouseKeeping() {
		// Any closing duties go here
		logger.trace("Storing data...");
		writeNumberOfUsers();
		ListOfUsers.serializeUsers();
		logger.trace("Data stored. System close.");
		logger.trace("=============================");
		input.close();	
	}
	
	// File to keep track of any persistent data. Currently only has numberOfUsers.
	private static void writeNumberOfUsers() {
		/*// Read the number of users from the data file
		try (FileWriter output = new FileWriter(new File("src/main/resources/data.txt"))) {
			output.write(ListOfUsers.getNumberOfUsers() + 48);
		} catch (IOException ioe) {
			logger.trace("Error storing data.");
		}*/
	}
}
