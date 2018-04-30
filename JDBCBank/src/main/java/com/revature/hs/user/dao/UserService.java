package com.revature.hs.user.dao;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

import com.revature.hs.user.Admin;
import com.revature.hs.user.Player;
import com.revature.hs.user.User;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.mindrot.jbcrypt.BCrypt;
import com.revature.hs.user.exceptions.*;


public class UserService {
	private static UserService instance = null;
	
	private static final Logger logger = Logger.getLogger(UserService.class);
	private static UserDao dao;
	
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
		setUser(user);
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
