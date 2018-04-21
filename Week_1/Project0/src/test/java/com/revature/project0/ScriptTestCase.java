package com.revature.project0;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;

import org.junit.Test;

public class ScriptTestCase {

	@Test(expected = UserTypeNotFoundException.class)
	public void typeNotFoundExceptionTest() throws FileNotFoundException {
	    Script.start(new FileReader("src/test/resources/UserTypeNotFoundScript.txt"));
	}
	
	@Test(expected = UserNotFoundException.class)
	public void userNotFoundExceptionTest() throws FileNotFoundException {
		 Script.start(new FileReader("src/test/resources/UserNotFoundScript.txt"));
	}
	
	@Test(expected = ApprovalPendingException.class)
	public void approvalExceptionTest() throws FileNotFoundException {
		 Script.start(new FileReader("src/test/resources/ApprovalPendingException.txt"));
	}
	
	@Test(expected = LockedAccountException.class)
	public void lockedAccountExceptionTest() throws FileNotFoundException {
		 Script.start(new FileReader("src/test/resources/LockedAccountException.txt"));
	}
	
	@Test(expected = PasswordIncorrectException.class)
	public void passwordIncorrectExceptionTest() throws FileNotFoundException {
		 Script.start(new FileReader("src/test/resources/PasswordIncorrectException.txt"));
	}
	

}
