package com.revature.logger;

import org.apache.log4j.Logger;

/**
 * Static logger so a bunch of loggers don't need to be created
 * @author Jesse
 *
 */

public class BankLogger {

	private BankLogger() {}
	
	public static Logger logger = Logger.getLogger(BankLogger.class);
	
}
