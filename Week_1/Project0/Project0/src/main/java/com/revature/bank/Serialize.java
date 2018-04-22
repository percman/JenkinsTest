package com.revature.bank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.HashMap;

public class Serialize {

	// Serialize Admin
	public static void serializeAdmin(File file) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File(file.getPath()), false));
			// Serializes Person object to file destination
			out.writeObject(Admin.adminMap);
			
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			try {
				// Close resources
				out.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	// Deserialize Admin
	public static void deserializeAdmin(File file) {
		if(file.isFile() && file.length() == 0) return;
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream(new FileInputStream(new File(file.getPath())));
			Admin.adminMap = (HashMap) in.readObject();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		finally {
			try {
				in.close();
			}catch(IOException ioe) {
				ioe.printStackTrace();
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
			ioe.printStackTrace();
		}
		finally {
			try {
				// Close resources
				out.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	// Deserialize User
	public static void deserializeUser(File file) {
		if(file.isFile() && file.length() == 0) return;
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(new File(file.getPath())));
			User.userMap = (HashMap) in.readObject();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		finally {
			try {
				in.close();
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
