package com.revature.hs.user.dao;

import com.revature.hs.card.Card;
import com.revature.hs.user.Admin;
import com.revature.hs.user.Player;
import com.revature.hs.user.User;
import com.revature.hs.user.exceptions.*;
import org.apache.log4j.Logger;


public class UserService {
	private static UserService instance = null;
	
	private static final Logger logger = Logger.getLogger(UserService.class);
	private static UserDao dao = UserDaoImpl.getInstance();
	
	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	public static String getStoredHash(String username) {
		return dao.getStoredHash(username);
	}

	public static String hashPassword(String username, String password) {
		return dao.hashPassword(username, password);
	}

	public static User login(String username, String password) throws NoSuchUserException, WrongPasswordException,
		LockedAccountException, UnApprovedUserException {
		User us;
		String storedHash = getStoredHash(username);
		String freshHash = hashPassword(username, password);

		if (!storedHash.equals(freshHash)) {
			throw new WrongPasswordException();
		}
		us = getUser(username);
		us.setPassword(password);

		if (us.isLocked()) {
			throw new LockedAccountException();
		}
		if (us instanceof Player && !((Player) us).isApproved()) {
			throw new UnApprovedUserException();
		}
		logger.debug("User " + username + " logged in successfully!");
		return us;
	}


	// This is my factory method, getUser will return either a Player or an Admin, determined at runtime.
	public static User getUser(String username) throws NoSuchUserException {
		if (isUser(username)) {
			return dao.getUser(username);
		}
		throw new NoSuchUserException();
	}

	public static boolean isUser(String username) {
		return dao.isUser(username);
	}

	public static void addUser(User user) throws DuplicateUserNameException {
		if (isUser(user.getUserName())) {
			throw new DuplicateUserNameException();
		}
		if (user.getRole().equals("player")) {
			logger.info("Adding new player...");
			dao.addPlayer((Player) user);
		} else {
			logger.info("Adding new admin...");
			dao.addAdmin((Admin) user);
		}
	}

	public static void addCard(Player player, Card card) {
		dao.addCard(player, card);
	}

	public static void setUser(User user) {
		if (user.getRole().equals("player")) {
			updatePlayer((Player) user);
		}
		else {
			updateAdmin((Admin) user);
		}
	}

	public static void updateAdmin(Admin admin) {
		dao.updateAdmin(admin);
	}

	public static void updatePlayer(Player player) {
		dao.updatePlayer(player);
	}


}
