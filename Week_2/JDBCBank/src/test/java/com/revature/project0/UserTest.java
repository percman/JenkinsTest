package com.revature.project0;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class UserTest {

	@Test
	//test to see if movies can be added to a users library correctly
	public void addTest() throws AlreadyHaveMovieException {
		User user = new User("daniel", "password");
		user.addMovie("Batman Begins");
		user.addMovie("The Dark Knight");
		user.addMovie("The Dark Knight Rises");
		Iterator<String> itr = user.movies.iterator();
		assertEquals(itr.next(), "Batman Begins");
		assertEquals(itr.next(), "The Dark Knight");
		assertEquals(itr.next(), "The Dark Knight Rises");
	}
	@Test
	//test to see if movies can be added if it already exists in a users library
	public void addExistingTest() throws AlreadyHaveMovieException {
		User user = new User("daniel", "password");
		user.addMovie("Batman Begins");
		user.addMovie("Batman Begins");
		user.addMovie("The Dark Knight");
		Iterator<String> itr = user.movies.iterator();
		itr.next();
		assertNotEquals(itr.next(), "Batman Begins");
	}
	@Test
	//test to see if movies can be removed from a users library correctly
	public void removeTest() throws MovieNotFoundException, NoMovieException, AlreadyHaveMovieException {
		User user = new User("daniel", "password");
		user.addMovie("Batman Begins");
		user.addMovie("The Dark Knight");
		user.addMovie("The Dark Knight Rises");
		user.removeMovie("Batman Begins");
		Iterator<String> itr = user.movies.iterator();
		assertEquals(itr.next(), "The Dark Knight");
		user.removeMovie("The Dark Knight");
		itr = user.movies.iterator();
		assertEquals(itr.next(), "The Dark Knight Rises");
	}
	@Test
	//test to see if movies can be removed if they  don't exists in a users library
	public void removeNoExistentTest() throws AlreadyHaveMovieException, MovieNotFoundException, NoMovieException {
		User user = new User("daniel", "password");
		user.addMovie("Batman Begins");
		user.addMovie("The Dark Knight");
		user.addMovie("The Dark Knight Rises");
		user.removeMovie("The Avengers");
		Iterator<String> itr = user.movies.iterator();
		assertEquals(itr.next(), "Batman Begins");
	}
}
