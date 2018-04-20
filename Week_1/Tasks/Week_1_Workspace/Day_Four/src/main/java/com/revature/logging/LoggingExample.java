package com.revature.logging;

import org.apache.log4j.Logger;

public class LoggingExample {
	
	private static final Logger logger = Logger.getLogger(LoggingExample.class);
	
	public static void main(String[] args) {
//		System.out.println("Hello World!");
//		logger.info("Hello World");
		
		try {
			divide();
		} catch (ArithmeticException e) {
			logger.warn("Somebody can't do math", e);
		}
		
		
	}

	
	public static int divide() {
		return 10 / 0;
	}
}
