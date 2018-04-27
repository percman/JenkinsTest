package com.revature.serialization;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutputStream;
	import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.users.Admin;


	public class AdminSerializer {
		private static final Logger logger = Logger.getLogger(AdminSerializer.class);
		// puts a user into a file
		public static void serializeAdmin(Set<Admin> admins, File file) {
			try (ObjectOutputStream userOutStream = new ObjectOutputStream(new FileOutputStream(file.getPath()))) {
					userOutStream.writeObject(admins);
			} catch (FileNotFoundException fnfe) {
				logger.error(fnfe.getMessage(),fnfe);
			} catch (IOException ioe) {
				logger.error(ioe.getMessage(),ioe);
			}

		}

		// pulls down a user from a file
		public static Set<Admin> deSerializeAdmin(File file) {
			try (ObjectInputStream userInStream = new ObjectInputStream(new FileInputStream(file.getPath()))) {
				return(Set<Admin>) userInStream.readObject();
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

