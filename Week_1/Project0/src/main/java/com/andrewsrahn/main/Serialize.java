package com.andrewsrahn.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class Serialize{
	public static void serialize(Bank b, File f) {		
		try(ObjectOutputStream out = 	
				new ObjectOutputStream(
						new FileOutputStream(
								new File( f.getPath() )))){
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, User> deserializeUsers(File file) {
		try(ObjectInputStream in = 
				new ObjectInputStream(
					new FileInputStream(
						new File(file.getPath())))){
			Bank b = (Bank) in.readObject();
			return b.getUsers();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String, Administrator> deserializeAdministrators(File file) {
		try(ObjectInputStream in = 
				new ObjectInputStream(
					new FileInputStream(
						new File(file.getPath())))){
			Bank b = (Bank) in.readObject();
			return b.getAdministrators();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}