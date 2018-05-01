package com.revature.project0;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.revature.dao.movie.Movie;
import com.revature.dao.movie.MovieDao;
import com.revature.dao.movie.MovieDaoImpl;
import com.revature.dao.movie.MovieService;
import com.revature.dao.users.AdminService;
import com.revature.dao.users.UserService;
import com.revature.exceptions.AlreadyHaveMovieException;
import com.revature.exceptions.MovieNotFoundException;
import com.revature.exceptions.NoMovieException;
import com.revature.exceptions.NotRentingMovieException;
import com.revature.exceptions.UserNotFoundException;

public class MovieTest {

	@Test
	@Ignore
	public void addNewMovieTest() throws MovieNotFoundException {
		AdminService.addNewMovie(new Movie("The Avengers"));
		assertEquals(MovieService.getMovie("The Avengers").getTitle(), "The Avengers");
	}

	@Test(expected = MovieNotFoundException.class)
	public void getMovieTest() throws MovieNotFoundException {
		MovieService.getMovie("Not a Title");
	}

	@Test(expected = MovieNotFoundException.class)
	public void rentMovieNoFoundTest() throws AlreadyHaveMovieException, MovieNotFoundException, UserNotFoundException {
		assertTrue(MovieService.RentMovie(UserService.getUser("megan"), "Not a Title"));
	}

	@Test
	@Ignore
	public void rentMovieTest() throws AlreadyHaveMovieException, MovieNotFoundException, UserNotFoundException {
		assertTrue(MovieService.RentMovie(UserService.getUser("megan"), "The Avengers"));
	}

	@Test(expected = AlreadyHaveMovieException.class)
	@Ignore
	public void rentAlreadyHaveTest() throws AlreadyHaveMovieException, MovieNotFoundException, UserNotFoundException {
		MovieService.RentMovie(UserService.getUser("megan"), "The Avengers");
	}

	@Test
	public void viewRentedMoviesTest() throws NoMovieException, UserNotFoundException, AlreadyHaveMovieException, MovieNotFoundException {
		MovieDao dao = MovieDaoImpl.getInstance();
		MovieService.RentMovie(UserService.getUser("megan"), "The Avengers");
		List<Movie> movies = dao.viewRentedMovies(UserService.getUser("megan"));
		assertEquals(movies.get(0).getTitle(), "The Avengers");
	}

	@Test
	public void returnMovieTest()
		throws UserNotFoundException, NotRentingMovieException, NoMovieException, MovieNotFoundException, AlreadyHaveMovieException {
		MovieService.RentMovie(UserService.getUser("megan"), "The Avengers");
		assertTrue(MovieService.ReturnMovie(UserService.getUser("megan"), "The Avengers"));
	}

	@Test(expected = NotRentingMovieException.class)
	public void returnMovieNotRentingTest() throws UserNotFoundException, NotRentingMovieException, NoMovieException, MovieNotFoundException, AlreadyHaveMovieException {
		AdminService.addNewMovie(new Movie("Superman"));
		MovieService.RentMovie(UserService.getUser("megan"), "The Avengers");
		assertTrue(MovieService.ReturnMovie(UserService.getUser("megan"), "Superman"));
	}

	@Test(expected = NoMovieException.class)
	@Ignore
	public void returnMovieNoMoviesTest()
			throws UserNotFoundException, NotRentingMovieException, NoMovieException, MovieNotFoundException {
		MovieService.ReturnMovie(UserService.getUser("megan"), "The Avengers");
	}

	@Test(expected = MovieNotFoundException.class)
	public void returnMovieNotFoundTest()
			throws UserNotFoundException, NotRentingMovieException, NoMovieException, MovieNotFoundException {
		assertTrue(MovieService.ReturnMovie(UserService.getUser("megan"), "Not a movie"));
	}

	@Test(expected = NoMovieException.class)
	@Ignore
	public void viewRentedNoMoviesTest() throws NoMovieException, UserNotFoundException {
		MovieDao dao = MovieDaoImpl.getInstance();
		List<Movie> movies = dao.viewRentedMovies(UserService.getUser("stan"));
	}

}
