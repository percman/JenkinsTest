package com.revature.dao.movie;

import java.util.List;

import com.revature.exceptions.AlreadyHaveMovieException;
import com.revature.exceptions.MovieNotFoundException;
import com.revature.exceptions.NoMovieException;
import com.revature.exceptions.NotRentingMovieException;
import com.revature.exceptions.OutOfStockException;
import com.revature.exceptions.tooManyMoviesOutException;
import com.revature.users.User;

public interface MovieDao {

	public List<Movie> viewRentedMovies(User user) throws NoMovieException;
	public boolean RentMovie(User user,String movie) throws AlreadyHaveMovieException, MovieNotFoundException;
	public boolean ReturnMovie(User user,String movie) throws NotRentingMovieException, MovieNotFoundException, NoMovieException;
	public void viewAvailableMovies();
	public Movie getMovie(String title) throws MovieNotFoundException;
}
