package com.revature.dao.movie;

public interface MovieDao {

	public void viewRentedMovies();
	public boolean RentMovie(Movie movie);
	public boolean ReturnMovie(Movie movie);
}
