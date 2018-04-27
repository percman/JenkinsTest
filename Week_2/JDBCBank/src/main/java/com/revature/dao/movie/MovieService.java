package com.revature.dao.movie;



public class MovieService {
private static MovieDao dao = new MovieDaoImplementation();

private static MovieService instance;
private MovieService() {};
public static MovieService getInstance() {
	if(instance == null) {
		instance = new MovieService();
	}
	return instance;
}
public void viewRentedMovies() {
	dao.viewRentedMovies();
}
public boolean RentMovie(Movie movie) {
	return dao.RentMovie(movie);
}
public boolean ReturnMovie(Movie movie) {
	return dao.ReturnMovie(movie);
}
}
