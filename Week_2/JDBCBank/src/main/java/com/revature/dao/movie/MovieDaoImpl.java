package com.revature.dao.movie;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.connection.ConnectionUtil;
import com.revature.exceptions.AlreadyHaveMovieException;
import com.revature.exceptions.MovieNotFoundException;
import com.revature.exceptions.NoMovieException;
import com.revature.exceptions.NotRentingMovieException;
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
	public List<Movie> viewRentedMovies(User user) throws NoMovieException {
		// TODO prints all movies currently being rented by Movie
		List<Movie> movie = new ArrayList<Movie>();
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT title from MOVIE_renting WHERE movie_id IN(SELECT movie_id from  USER_MOVIE where user_id = ?)");
			stmt.setInt(++index, user.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				 movie.add(new Movie(rs.getString("title")));
			return movie;
		}catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State:" + sqle.getSQLState());
			System.err.println("SQL Code:" + sqle.getErrorCode());
		}
		return null;
	}
	@Override
	public boolean RentMovie(User user,String title) throws AlreadyHaveMovieException, MovieNotFoundException {
		// TODO allows User to rent up to 3 movies at a given time
		Movie movie = getMovie(title);
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				CallableStatement stmt = conn.prepareCall("{CALL rent_user_movie(?,?)}");
				stmt.setString(++index, user.getUsername());
				stmt.setString(++index, title);
				return stmt.executeUpdate() > 0;
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State:" + sqle.getSQLState());
				System.err.println("SQL Code:" + sqle.getErrorCode());
			}
		return false;
	}
	@Override
	public boolean ReturnMovie(User user,String title) throws NotRentingMovieException, MovieNotFoundException, NoMovieException {
		// TODO allows User to rent up to 3 movies at a given time
					Movie movie = getMovie(title);
					MovieDao dao = new MovieDaoImpl();
					if(dao.viewRentedMovies(user).size() > 0) {
					if(dao.viewRentedMovies(user).contains(movie)) {
					int index = 0;
					try(Connection conn = ConnectionUtil.getConnection()){
						CallableStatement stmt = conn.prepareCall("{CALL return_user_movie(?,?)}");
						stmt.setString(++index, user.getUsername());
						stmt.setString(++index, title);
						return stmt.executeUpdate() > 0;
					} catch (SQLException sqle) {
						System.err.println(sqle.getMessage());
						System.err.println("SQL State:" + sqle.getSQLState());
						System.err.println("SQL Code:" + sqle.getErrorCode());
					}
					}
						throw new NotRentingMovieException();
					}
					throw new NoMovieException();
	}
	@Override
	public void viewAvailableMovies() {
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movie_renting");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString("title"));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+sqle.getSQLState());
			System.err.println("Error message: "+sqle.getErrorCode());
		}
	}

	@Override
	public Movie getMovie(String title) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movie_renting WHERE title = ?");
			stmt.setString(++index, title);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return new Movie(rs.getString("title"),rs.getInt("movie_id"));
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+sqle.getSQLState());
			System.err.println("Error message: "+sqle.getErrorCode());
		}
		throw new MovieNotFoundException();
	}


}
