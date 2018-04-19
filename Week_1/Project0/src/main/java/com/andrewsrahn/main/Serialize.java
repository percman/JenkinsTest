package com.andrewsrahn.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialize{
	public static void serialize(Bank b, File f) {		
		try(ObjectOutputStream out = 	
				new ObjectOutputStream(
						new FileOutputStream(
								new File( f.getPath() )))){
			
		} catch(Exception e) {
			System.err.println(e.toString());
		}
	}
	
	public static Bank deserialize(File file) {
		try(ObjectInputStream in = 
				new ObjectInputStream(
					new FileInputStream(
						new File(file.getPath())))){
			return (Bank) in.readObject();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		return null;
	}
}