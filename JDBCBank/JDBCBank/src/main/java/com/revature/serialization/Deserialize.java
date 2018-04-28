package com.revature.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

import com.revature.singletons.AccountData;
import com.revature.singletons.LogThis;
import com.revature.users.Person;

public class Deserialize {

	private static AccountData ad = AccountData.getInstance();

	@SuppressWarnings("unchecked")
	// The suppressed warning comes from casting the object being read to a map
	public static void deserializeAccountData(File file) {

		if (file.exists()) {
			ObjectInputStream savedData = null;
			try {
				savedData = new ObjectInputStream(new FileInputStream(new File(file.getPath())));

				ad.setHashMap((Map<String, Person>) savedData.readObject());

				LogThis.info("Data was deserialized.");

			} catch (IOException ioe) {
				LogThis.warn(ioe.getMessage());
			} catch (ClassNotFoundException cnfe) {
				LogThis.warn(cnfe.getMessage());
			} finally {
				// I will beat this dead horse
				// close that resource
				try {
					savedData.close();
				} catch (IOException ioe) {
					LogThis.warn(ioe.getMessage());
				}
			}
		}
	}

}
