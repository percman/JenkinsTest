package com.revature.dao.movie;

import com.revature.exceptions.AlreadyHaveMovieException;
import com.revature.exceptions.NoMovieException;
import com.revature.exceptions.NotRentingMovieException;
import com.revature.exceptions.OutOfStockException;
import com.revature.exceptions.tooManyMoviesOutException;
import com.revature.users.User;

public class MovieService {
private static MovieDao dao = MovieDaoImpl.getInstance();


public static void viewRentedMovies(User user) throws NoMovieException {
	dao.viewRentedMovies(user);
}
public static boolean RentMovie(User user,String movie) throws tooManyMoviesOutException, OutOfStockException,AlreadyHaveMovieException{
	return dao.RentMovie(user,movie);
}
public static boolean ReturnMovie(User user,String movie) throws NotRentingMovieException {
	return dao.ReturnMovie(user,movie);
}
}
