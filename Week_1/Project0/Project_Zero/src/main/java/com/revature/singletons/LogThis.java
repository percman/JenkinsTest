package com.revature.singletons;

import org.apache.log4j.Logger;

public class LogThis {
	
	/*
	 * 3 things we need for a Singleton:
	 * - private static field, matching the type of out class
	 * - private constructor
	 * - public static getInstance() method, which will either instantiate or return the existing object in memory
	 */
	
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
