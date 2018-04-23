package com.revature.hs;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;



public class UserDB {
	private JSONObject DB;
	private static UserDB instance = null;
	
	private static final Logger logger = Logger.getLogger(UserDB.class);
	
	public static UserDB getInstance() {
		if (instance == null) {
			instance = new UserDB();
		}
		return instance;
	}
	
	public User login(String username, int passwordHash) throws NoSuchUserException, WrongPasswordException,
		LockedAccountException, UnApprovedUserException {

		User us = getUser(username);
		if (us.getPasswordHash() != passwordHash) {
			throw new WrongPasswordException();
		}
		if (us.isLocked()) {
			throw new LockedAccountException();
		}
		if (us instanceof Player && !((Player) us).isApproved()) {
			throw new UnApprovedUserException();
		}
		logger.debug("User " + username + "logged in successfully!");
		return us;
	}

	public Deque<User> getUsers() {
		Deque<User> dd = new ArrayDeque<>();
		JSONObject jso;
		String role = null;

		for (String s: DB.keySet()) {
			jso = DB.getJSONObject(s);
			role = jso.getString("role");
			if (role.equals("admin")) {
				dd.add(new Admin(jso));
			}
			else {
				dd.add(new Player(jso));
			}
		}

		return dd;
	}
	
	
	private UserDB() {
		long timer = System.currentTimeMillis();
		FileReader fr = null;
		try {
			fr = new FileReader(new File("src/main/resources/users.json"));
			DB = new JSONObject(new JSONTokener(fr));
		} catch (FileNotFoundException fnfe) {
			logger.warn("Couldn't find users.json", fnfe);
		} catch (JSONException jse) {
			logger.warn("Invalid JSON", jse);
		} finally {
			try {
				fr.close();
		} catch (IOException ioe){
			logger.warn(ioe.getMessage());
		}
		}
		logger.info("UserDB created, took " + ((System.currentTimeMillis() - timer) / 1000.0) + " seconds" );
	}
	
	private boolean isUser(String username) {
		try {
			DB.get(username);
			return true;
		} catch (JSONException e) {
			return false;
		}
	}

	private User getUser(String username) throws NoSuchUserException {
		JSONObject jso;
		try {
			jso = DB.getJSONObject(username);
		} catch (JSONException e) {
			logger.warn("User \"" + username +"\"doesn't exist");
			throw new NoSuchUserException("User \"" + username +"\"doesn't exist");
		}
		String role = jso.getString("role");
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
		DB.put(user.getUserName(), user.toJSONObject());
		writeToFile();
	}


	public void removeUser(String username) throws NoSuchUserException {
		if (isUser(username)) {
			DB.remove(username);
			writeToFile();
		}
		throw new NoSuchUserException();
	}

	private void writeToFile() {
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File("src/main/resources/users.json"));
			fw.write(DB.toString());
		} catch (IOException e) {
			logger.warn("Couldn't write userDB to file");
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				logger.warn("Failed to close FileWriter after printing userDB to file");
			}
		}
	}
}
