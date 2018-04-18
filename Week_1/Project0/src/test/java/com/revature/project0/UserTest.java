package com.revature.project0;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	//test to see if movies can be added to a users library correctly
	public void addTest() {
		User user = new User("daniel", "password");
		user.addMovie("Batman Begins");
		assertEquals(user.movies.get(0), "Batman Begins");
		user.addMovie("The Dark Knight");
		user.addMovie("The Dark Knight Rises");
		assertEquals(user.movies.get(1), "The Dark Knight");
		assertEquals(user.movies.get(2), "The Dark Knight Rises");
	}
	@Test
	//test to see if movies can be added if it already exists in a users library
	public void addExistingTest() {
		User user = new User("daniel", "password");
		user.addMovie("Batman Begins");
		user.addMovie("Batman Begins");
		user.addMovie("The Dark Knight");
		assertNotEquals(user.movies.get(1), "Batman Begins");
	}
	@Test
	//test to see if movies can be removed from a users library correctly
	public void removeTest() {
		User user = new User("daniel", "password");
		user.addMovie("Batman Begins");
		user.addMovie("The Dark Knight");
		user.addMovie("The Dark Knight Rises");
		user.removeMovie("Batman Begins");
		assertEquals(user.movies.get(0), "The Dark Knight");
		user.removeMovie("The Dark Knight");
		assertEquals(user.movies.get(0), "The Dark Knight Rises");
	}
	@Test
	//test to see if movies can be removed if they  don't exists in a users library
	public void removeNoExistentTest() {
		User user = new User("daniel", "password");
		user.addMovie("Batman Begins");
		user.addMovie("The Dark Knight");
		user.addMovie("The Dark Knight Rises");
		user.removeMovie("The Avengers");
		assertEquals(user.movies.get(0), "Batman Begins");
	}
	@Test
	//test to see if a user can be locked and unlocked correctly 
	public void lockTest() {
		User user = new User();
		user.setLocked(true);
		assertTrue(user.isUserLocked());
		user.setLocked(false);
		assertFalse(user.isUserLocked());
	}
	@Test
	//test to see if a user can be approved
	public void userApprovedTest() {
		User user = new User();
		assertFalse(user.isUserApproved());
		user.approved();
		assertTrue(user.isUserApproved());
	}

}
