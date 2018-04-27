package com.revature.exceptions;

public class UserTypeNotFoundException extends Exception{
		private static final long serialVersionUID = 925607534058303810L;
		
		public UserTypeNotFoundException() {
			super();
		}
		public UserTypeNotFoundException(String msg) {
			super(msg);
		}
		public UserTypeNotFoundException(String msg, Throwable cause) {
			super(msg,cause);
		}

	}

