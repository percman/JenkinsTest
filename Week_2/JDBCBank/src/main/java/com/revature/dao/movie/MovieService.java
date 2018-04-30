package com.revature.dao.movie;

import com.revature.dao.users.UserService;
import com.revature.exceptions.AlreadyHaveMovieException;
import com.revature.exceptions.MovieNotFoundException;
import com.revature.exceptions.NoMovieException;
import com.revature.exceptions.NotRentingMovieException;
import com.revature.exceptions.OutOfStockException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.tooManyMoviesOutException;
import com.revature.users.User;

public class MovieService {
private static MovieDao dao = MovieDaoImpl.getInstance();


public static void viewRentedMovies(User user) throws NoMovieException {
	for( Movie movie: dao.viewRentedMovies(user)) {
		System.out.println(movie.getTitle());
	}
}
public static boolean RentMovie(User user,String movie) throws AlreadyHaveMovieException, MovieNotFoundException{
	return dao.RentMovie(user,movie);
}
public static boolean ReturnMovie(User user,String movie) throws NotRentingMovieException, MovieNotFoundException, NoMovieException {
	return dao.ReturnMovie(user,movie);
}

public static void ViewAvailableMovies() {
	dao.viewAvailableMovies();
}

public static Movie getMovie(String title) throws MovieNotFoundException {
	return dao.getMovie(title);
}

public static void main(String[] args) throws tooManyMoviesOutException, OutOfStockException, AlreadyHaveMovieException, MovieNotFoundException, UserNotFoundException {
	//ViewAvailableMovies();
	//System.out.println(UserService.getUser("jimmy").getUsername());
	RentMovie(UserService.getUser("jimmy"),"Batman");
}

}
