package com.revature.serialization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import com.revature.singletons.LogThis;
import com.revature.users.Person;

public class Serialize {
	
	public static void serializeAccountData(Map<String, Person> data, File file) {

		ObjectOutputStream save = null;
		try {
			save = new ObjectOutputStream(new FileOutputStream(new File(file.getPath())));
			save.writeObject(data);
			LogThis.info("Data was Serialized.");
		} catch (IOException ioe) {
			LogThis.warn(ioe.getMessage());
		} finally {
			// gotta close those resources
			try {
				save.close();
			} catch (IOException ioe) {
				LogThis.warn(ioe.getMessage());
			}
		}

	}

}
