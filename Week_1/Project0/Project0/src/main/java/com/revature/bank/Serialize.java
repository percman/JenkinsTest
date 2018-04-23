package com.revature.bank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.apache.log4j.Logger;



public class Serialize {
	private static final Logger logger = Logger.getLogger(Serialize.class);

	// Serialize Admin
	public static void serializeAdmin(File file) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File(file.getPath()), false));
			// Serializes Person object to file destination
			out.writeObject(Admin.adminMap);
			
			
		}catch(IOException ioe) {
			logger.warn(ioe.getMessage());
		}
		finally {
			try {
				// Close resources
				out.close();
			}
			catch(IOException ioe) {
				logger.warn(ioe.getMessage());
			}
		}
	}
	
	// Deserialize Admin
	public static void deserializeAdmin(File file) {
		if(file.isFile() && file.length() == 0) return;
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream(new FileInputStream(new File(file.getPath())));
			Admin.adminMap = (HashMap<String, Admin>) in.readObject();
		}catch(IOException ioe) {
			logger.warn(ioe.getMessage());
		}catch(ClassNotFoundException cnfe) {
			logger.warn(cnfe.getMessage());
		}
		finally {
			try {
				in.close();
			}catch(IOException ioe) {
				logger.warn(ioe.getMessage());;
			}
		}
	}
	
	// Serialize User
	public static void serializeUser(File file) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File(file.getPath()), false));
			// Serializes Person object to file destination
			out.writeObject(User.userMap);
		}catch(IOException ioe) {
			logger.warn(ioe.getMessage());
		}
		finally {
			try {
				// Close resources
				out.close();
			}
			catch(IOException ioe) {
				logger.warn(ioe.getMessage());
			}
		}
	}
	
	// Deserialize User
	public static void deserializeUser(File file) {
		if(file.isFile() && file.length() == 0) return;
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(new File(file.getPath())));
			User.userMap = (HashMap<String, User>) in.readObject();
		}catch(IOException ioe) {
			logger.warn(ioe.getMessage());
		}catch(ClassNotFoundException cnfe) {
			logger.warn(cnfe.getMessage());
		}
		finally {
			try {
				in.close();
			}catch(IOException ioe) {
				logger.warn(ioe.getMessage());;
			}
		}
	}
}
