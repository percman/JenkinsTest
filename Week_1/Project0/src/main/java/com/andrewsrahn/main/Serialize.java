package com.andrewsrahn.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class Serialize{
	public static void serializeAdministrators(Map<String, Administrator> administrators, Logger logger) {
		for(Administrator administrator: administrators.values()) {
			String fileName = "src/main/resources/administrators/" + administrator.getName();
			
			try(ObjectOutputStream o = 	new ObjectOutputStream(
											new FileOutputStream(
												new File(fileName)))){
				o.writeObject(administrator);
				logger.trace("serialized " + administrator.getName());
			} catch(Exception e) {
				logger.error(e.getMessage());
			}
		}
		
	}
	
	public static void serializeUsers(Map<String, User> users, Logger logger) {
		for(User user: users.values()) {
			String fileName = "src/main/resources/users/" + user.getName();
			
			try(ObjectOutputStream o = new ObjectOutputStream(
											new FileOutputStream(
													new File(fileName)))){
				o.writeObject(user);
				logger.trace("serialized " + user.getName());
			} catch(Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
	
	public static Map<String, User> deserializeUsers(Logger logger) {
		File[] files = new File("src/main/resources/users").listFiles();
		Map<String, User> users = new HashMap<>();
		for(File file: files) {
			try(ObjectInputStream o = 	new ObjectInputStream(
											new FileInputStream(file))){
				User user = (User) o.readObject();
				logger.trace("deserialized " + user.getName());
				users.put(user.getName(), user);
			} catch(Exception e) {
				logger.error(e.getMessage());
			}
			
		}
		return users;
	}
	
	public static Map<String, Administrator> deserializeAdministrators(Logger logger) {
		Map<String, Administrator> administrators = new HashMap<>();
		File[] files = new File("src/main/resources/administrators").listFiles();
		for(File file: files) {
			try(ObjectInputStream o = 	new ObjectInputStream(
											new FileInputStream(file))){
				Administrator administrator = (Administrator) o.readObject();
				logger.trace("deserialized " + administrator.getName());
				administrators.put(administrator.getName(), administrator);
			} catch(Exception e) {
				logger.error(e.getMessage());
			}
		}
		return administrators;
	}
}