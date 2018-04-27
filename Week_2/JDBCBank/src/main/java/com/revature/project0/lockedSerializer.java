package com.revature.project0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

import org.apache.log4j.Logger;

public class lockedSerializer {
	private static final Logger logger = Logger.getLogger(UserSerializer.class);
	// puts a user into a file
	public static void serializeUser(Set<String> users, File file) {
		try (ObjectOutputStream userOutStream = new ObjectOutputStream(new FileOutputStream(file.getPath()))) {
				userOutStream.writeObject(users);
		} catch (FileNotFoundException fnfe) {
			logger.error(fnfe.getMessage(),fnfe);
		} catch (IOException ioe) {
			logger.error(ioe.getMessage(),ioe);
		}

	}

	// pulls down a user from a file
	public static Set<String> deSerializelockedUser(File file) {
		try (ObjectInputStream userInStream = new ObjectInputStream(new FileInputStream(file.getPath()))){
			return(Set<String>) userInStream.readObject();
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
