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
	
	public User login(String username, String password) throws NoSuchUserException, WrongPasswordException,
		LockedAccountException, UnApprovedUserException {

		User us = getUser(username);
		if (!BCrypt.checkpw(password, us.getPasswordHash())) {
			throw new WrongPasswordException();
		}
		if (us.isLocked()) {
			throw new LockedAccountException();
		}
		if (us instanceof Player && !((Player) us).isApproved()) {
			throw new UnApprovedUserException();
		}
		logger.debug("User " + username + " logged in successfully!");
		return us;
	}

	private boolean isUser(String username) {
		return dao.isUser(username);
	}

	public static User getUser(String username) throws NoSuchUserException {

		if (role.equals("player")) {
			return new Player(jso);
		}
		else {
			return new Admin(jso);
		}
	}

	public void addUser(User user) throws DuplicateUserNameException {
		if (isUser(user.getUserName())) {
			throw new DuplicateUserNameException();
		}
		setUser(user);
	}


	public void setUser(User user) {
		dao.setUser(user);
	}


}
