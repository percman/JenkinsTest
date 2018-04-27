package com.revature.project0;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import com.revature.exceptions.AlreadyHaveMovieException;

public class SerializationTest {

	@Test
	
	// test to see if a user can be properly serialized and deserialized
	public void deSerializeTest() throws AlreadyHaveMovieException {
		User user = new User("daniel", "password");
		user.addMovie("Batman Begins");
		Set<User> users = new HashSet<>();
		users.add(user);
		UserSerializer.serializeUser(users,new File("src/test/resources/testUser.txt"));
		users.clear();
		users = UserSerializer.deSerializeUser(new File("src/test/resources/testUser.txt"));
		Iterator<User> itr = users.iterator();
		User deUser = itr.next();
		assertEquals(user.username, deUser.username);
		assertEquals(user.password, deUser.password);
		assertEquals(user.movies, deUser.movies);
	}

}
