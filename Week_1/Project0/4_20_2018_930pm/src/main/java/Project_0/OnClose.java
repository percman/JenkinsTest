package Project_0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class OnClose {
	
	public static void programHouseKeeping() {
		// Any closing duties go here
		Initialization.logger.trace("Storing data...");
		writeNumberOfUsers();
		ListOfUsers.serializeUsers();
		Initialization.logger.trace("Data stored. System close.");
		
	}
	
	private static void writeNumberOfUsers() {
		// Read the number of users from the data file
		try (FileWriter output = new FileWriter(new File("src/main/resources/data.txt"))) {
			output.write(ListOfUsers.getNumberOfUsers() + 48);
		} catch (IOException ioe) {
			Initialization.logger.trace("Error storing data.");
		}
	}
}
