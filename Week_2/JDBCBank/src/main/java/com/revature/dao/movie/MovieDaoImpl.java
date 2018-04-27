package com.revature.dao.movie;

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
	public void viewRentedMovies() {
		// TODO prints all movies currently being rented by Movie
		
	}

	@Override
	public boolean RentMovie(Movie movie) {
		// TODO allows User to rent up to 5 movies at a given time
		// TODO sequence rented movies
		return false;
	}

	@Override
	public boolean ReturnMovie(Movie movie) {
		// TODO allows User to return their movies
		// TODO sequence remove movies
		return false;
	}

}
