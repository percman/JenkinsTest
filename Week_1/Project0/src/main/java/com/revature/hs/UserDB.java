package com.revature.hs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;



public class UserDB {
	private JSONArray DB;
	private static UserDB instance = null;
	
	private static final Logger logger = Logger.getLogger(UserDB.class);
	
	private static UserDB getInstance() {
		if (instance == null) {
			instance = new UserDB();
		}
		return instance;
	}
	
	//TODO: implement
	public User login(String username, int passwordHash) throws NoSuchUserException, WrongPasswordException {
		return new User("", 0);
	}
	
	//TODO: implement
	public Deque<User> getUsers() {
		Deque<User> dd = new ArrayDeque<User>();
		JSONObject jso;
		for (Object o: DB) {
			jso = (JSONObject) o;
			jso.get("role");
		}
		
		return ;
	}
	
	private Player jsoToPlayer(JSONObject jso) {
		
	}
	
	private UserDB() {
		FileReader fr = null;
		try {
			fr = new FileReader(new File("src/main/resources/users.json"));
			DB = new JSONArray(new JSONTokener(fr));
		} catch (FileNotFoundException fnfe) {
			logger.warn("Couldn't find users.json", fnfe);
		} finally {
			try {
				fr.close();
		} catch (IOException ioe){
			logger.warn(ioe.getMessage());
		}
		}
	}
	
	//TODO: implement
	public void addUser(Player user) {
		
	}
	
	//TODO: implement
	public void addUser(Admin user) {
		
	}
	
	//TODO: implement
	public void removeUser(String username) throws NoSuchUserException {
		
	}
}
