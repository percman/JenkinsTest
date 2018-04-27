package com.revature.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.apache.log4j.Logger;

public class PasswordSerializer {
	private static final Logger logger = Logger.getLogger(AdminSerializer.class);
	// puts a user into a file
	public static void serializePasswords(Map<String, String> passwords, File file) {
		try (ObjectOutputStream passOutStream = new ObjectOutputStream(new FileOutputStream(file.getPath()))) {
				passOutStream.writeObject(passwords);
		} catch (FileNotFoundException fnfe) {
			logger.error(fnfe.getMessage(),fnfe);
		} catch (IOException ioe) {
			logger.error(ioe.getMessage(),ioe);
		}

	}

	// pulls down a user from a file
	public static Map<String, String> deSerializePasswords(File file) {
		try (ObjectInputStream passInStream = new ObjectInputStream(new FileInputStream(file.getPath()))) {
			return(Map<String, String>) passInStream.readObject();
		} catch (FileNotFoundException fnfe) {
			logger.error(fnfe.getMessage(),fnfe);
		} catch (IOException ioe) {
			logger.error(ioe.getMessage(),ioe);
		} catch (ClassNotFoundException cnfe) {
			logger.error(cnfe.getMessage(),cnfe);
		}
		return null;
	}
}

