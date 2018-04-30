package com.bank.singleton;

import org.apache.log4j.Logger;

public class LogSingleton {
	
	private static LogSingleton instance;
	
	private LogSingleton() {}
	
	private static final Logger logger = Logger.getLogger(Logger.class);
	
	public static LogSingleton getInstance() {
		if (instance == null) {
			instance = new LogSingleton();
		}
		return instance;
	}
	
	public static void info(String msg) {
		logger.info(msg);
	}
	
	public static void warn(String msg, Exception e) {
		logger.warn(msg);
	}

}
