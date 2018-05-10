package com.revature.logging;

import org.apache.log4j.Logger;

public class LogThis {
	
	
	private static LogThis instance;
	
	private static Logger logger = Logger.getLogger(LogThis.class);

	
	private LogThis() {
		 logger = Logger.getLogger(LogThis.class);
	}
	
	public static LogThis getInstance() {
		if(instance == null) {
			instance = new LogThis();
		}
		return instance;
	}
	
	public static void info(Object msg) {
		logger.info(msg);
	}
	
	public static void warn(String msg) {
		logger.warn(msg);
	}

}
