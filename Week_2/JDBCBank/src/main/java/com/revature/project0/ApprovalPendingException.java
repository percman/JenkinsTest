package com.revature.project0;

public class ApprovalPendingException extends Exception{
	private static final long serialVersionUID = 925607534058303810L;
	
	public ApprovalPendingException() {
		super();
	}
	public ApprovalPendingException(String msg) {
		super(msg);
	}
	public ApprovalPendingException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
