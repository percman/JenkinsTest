package com.revature.hs.user;

import com.revature.hs.*;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.mindrot.jbcrypt.BCrypt;
import com.revature.hs.user.exceptions.*;

public class Admin extends User {
	private static final Logger logger = Logger.getLogger(Admin.class);

	public Admin(JSONObject jso) {
		super(jso);
	}

	public Admin(String userName, String passwordHash, String role) {
		super(userName, passwordHash, role);
	}

	public void addAdmin() {
		TextIO textIO = TextIoFactory.getTextIO();
		String user = textIO.newStringInputReader().withMinLength(4).withIgnoreCase().read("enter username");
		String passwordHash = BCrypt.hashpw(textIO.newStringInputReader().withMinLength(6).read("enter password"),
				BCrypt.gensalt());
		try {
			UserDB.getInstance().addUser(new Admin(user, passwordHash, "admin"));
		} catch (DuplicateUserNameException e) {
			logger.info("Attempted admin creation with duplicate username " + user);
			TextTerminal terminal = textIO.getTextTerminal();
			terminal.println("Sorry, an user with that name already exists");
		}
	}

	//TODO: offer up a list of users
	public void lockUser() {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal terminal = textIO.getTextTerminal();
		UserDB DB = UserDB.getInstance();
		String user = textIO.newStringInputReader().withMinLength(4).withIgnoreCase().read(
				"Which user do you want to lock?");
		try {
			User us = DB.getUser(user);
			us.setLocked(true);
			DB.setUser(us);
			logger.info(user + " locked.");
			terminal.println("User locked!");
		} catch (NoSuchUserException e) {
			logger.warn("No such user exists.");
			terminal.println("No such user exists.");
		}
	}

	//TODO: offer up a list of users
	public void unlockUser() {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal terminal = textIO.getTextTerminal();
		UserDB DB = UserDB.getInstance();
		String user = textIO.newStringInputReader().withMinLength(4).withIgnoreCase().read(
				"Which user do you want to unlock?");
		try {
			User us = DB.getUser(user);
			us.setLocked(false);
			DB.setUser(us);
			logger.info(user + " unlocked.");
			terminal.println("User unlocked!");
		} catch (NoSuchUserException e) {
			logger.warn("No such user exists.");
			terminal.println("No such user exists.");
		}
	}

	//TODO: offer up a list of users
	public void approveUsers() {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal terminal = textIO.getTextTerminal();
		UserDB DB = UserDB.getInstance();
		String user = textIO.newStringInputReader().withMinLength(4).withIgnoreCase().read(
				"Which user do you want to approve?");
		try {
			User us = DB.getUser(user);
			((Player) us).setApproved(true);
			DB.setUser(us);
			logger.info(user + " approved.");
			terminal.println("User approved!");
		} catch (NoSuchUserException e) {
			logger.warn("No such user exists.");
			terminal.println("No such user exists.");
		}
	}
}
