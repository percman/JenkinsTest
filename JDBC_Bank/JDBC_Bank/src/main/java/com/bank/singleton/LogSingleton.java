package com.bank.singleton;

import org.apache.log4j.Logger;

public class LogSingleton {
	
	private static LogSingleton instance;
	
	private LogSingleton() {}
	
	public static LogSingleton getInstance() {
		if (instance == null) {
			instance = new LogSingleton();
		}
		return instance;
	}
	
	private static final Logger logger = Logger.getLogger(Logger.class);
}
