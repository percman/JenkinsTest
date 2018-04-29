package com.revature.logger;

import org.apache.log4j.Logger;

public class BankLogger {

	private BankLogger() {}
	
	public static Logger logger = Logger.getLogger(BankLogger.class);
	
}
