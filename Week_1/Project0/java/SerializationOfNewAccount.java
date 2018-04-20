package com.revature.project_0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationOfNewAccount {
	/*
	 * Class that serializes and Deserializes our Account objects and UserList object
	 */
	public static void SerializeAccount(Account account,File file) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File(file.getPath())));
			out.writeObject(account);
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void SerializeList(UserList list) {
		ObjectOutputStream out = null;
		String target = "src/main/resources/pendingUsers.txt";
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File(target)));
			out.writeObject(list);
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * When signing in, the all accounts will be deserialized using this method.
	 * It returns an Account type object because both the Users and Admin inherit from this class. Useful when 
	 * signing in because there is no way of knowing if the person signing in is a user or an admin until their file is deserialized
	 */
	public static Account deserializeAccount(File file) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(new File(file.getPath())));
			return (Account) in.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return null;
	}
	
	//This method is used for admin operations such as locking and unlocking accounts
	public static User deserializeUser(File file) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(new File(file.getPath())));
			return (User) in.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return null;
	}
	
	//Deserialize the UserList object
	public static UserList deserializeList() {
		ObjectInputStream in = null;
		String list = "src/main/resources/pendingUsers.txt";
		try {
			in = new ObjectInputStream(new FileInputStream(new File(list)));
			return (UserList) in.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return null;
	}
	
		
}
