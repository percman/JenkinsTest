package com.revature.dao.movie;

import com.revature.exceptions.AlreadyHaveMovieException;
import com.revature.exceptions.NoMovieException;
import com.revature.exceptions.NotRentingMovieException;
import com.revature.exceptions.OutOfStockException;
import com.revature.exceptions.tooManyMoviesOutException;
import com.revature.users.User;

public class MovieDaoImpl implements MovieDao {

	
private static MovieDaoImpl instance;
	
	private MovieDaoImpl() {}
	
	public static MovieDaoImpl getInstance() {
		if(instance == null) {
			instance = new MovieDaoImpl();
		}
		return instance;
	}
	
	
	@Override
	public void viewRentedMovies(User user) throws NoMovieException {
		// TODO prints all movies currently being rented by Movie
		
	}

	@Override
	public boolean RentMovie(User user,String movie) throws tooManyMoviesOutException, OutOfStockException,AlreadyHaveMovieException {
		// TODO allows User to rent up to 5 movies at a given time
		// TODO sequence rented movies
		return false;
	}

	@Override
	public boolean ReturnMovie(User user,String movie) throws NotRentingMovieException {
		// TODO allows User to return their movies
		// TODO sequence remove movies
		return false;
	}

}
