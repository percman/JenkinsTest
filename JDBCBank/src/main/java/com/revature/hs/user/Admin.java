package com.revature.hs.user;

import com.revature.hs.user.dao.UserService;
import com.revature.hs.user.exceptions.DuplicateUserNameException;
import com.revature.hs.user.exceptions.NoSuchUserException;
import org.apache.log4j.Logger;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;


import static com.revature.hs.user.dao.UserService.*;

public class Admin extends User {
	private static final Logger logger = Logger.getLogger(Admin.class);

	public Admin(String userName, String password, String role) {
		super(userName, password, role);
	}

	public Admin(int id, String username, boolean isLocked) {
		super(id, username, isLocked, "admin");
	}

	public void addAdmin() {
		TextIO textIO = TextIoFactory.getTextIO();
		String user = textIO.newStringInputReader().withMinLength(4).withIgnoreCase().read("enter username");
		String password = textIO.newStringInputReader().withMinLength(6).read("enter password");
		try {
			addUser(new Admin(user, password, "admin"));
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

		String user = textIO.newStringInputReader().withMinLength(4).withIgnoreCase().read(
				"Which user do you want to lock?");
		try {
			User us = getUser(user);
			us.setLocked(true);
			setUser(us);
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
		String user = textIO.newStringInputReader().withMinLength(4).withIgnoreCase().read(
				"Which user do you want to unlock?");
		try {
			User us = getUser(user);
			us.setLocked(false);
			setUser(us);
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
		UserService DB = UserService.getInstance();
		String user = textIO.newStringInputReader().withMinLength(4).withIgnoreCase().read(
				"Which user do you want to approve?");
		try {
			User us = getUser(user);
			((Player) us).setApproved(true);
			setUser(us);
			logger.info(user + " approved.");
			terminal.println("User approved!");
		} catch (NoSuchUserException e) {
			logger.warn("No such user exists.");
			terminal.println("No such user exists.");
		}
	}
}
