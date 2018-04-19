package com.revature.zero.Project_Zero;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationUser {
	
	public static void serializeUser(User user, File file) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(user);
			out.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//User user = new User(1, true, false);
		//serializeUser(user, new File("src/main/java/camerom.txt"));
	}
}
