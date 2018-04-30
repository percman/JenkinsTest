package com.revature.project_0;

import static com.revature.scanner.BankScanner.*;
import static com.revature.logger.BankLogger.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for any closing duties to be performed. Currently empty.
 * @author Jesse
 *
 */

public class OnClose {
	
	public static void programHouseKeeping() {
		// Any closing duties go here
		logger.trace("Storing data...");
		logger.trace("Data stored. System close.");
		logger.trace("=============================");
		input.close();	
	}
}
