package com.revature.dao.movie;

import com.revature.exceptions.AlreadyHaveMovieException;
import com.revature.exceptions.NoMovieException;
import com.revature.exceptions.NotRentingMovieException;
import com.revature.exceptions.OutOfStockException;
import com.revature.exceptions.tooManyMoviesOutException;
import com.revature.users.User;

public interface MovieDao {

	public void viewRentedMovies(User user) throws NoMovieException;
	public boolean RentMovie(User user,String movie) throws tooManyMoviesOutException, OutOfStockException,AlreadyHaveMovieException;
	public boolean ReturnMovie(User user,String movie) throws NotRentingMovieException;
}
