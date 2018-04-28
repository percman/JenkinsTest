package com.revature.dao.users;

import java.util.List;

import com.revature.dao.movie.Movie;
import com.revature.exceptions.UserNotFoundException;
import com.revature.users.Admin;


public interface AdminDao {

	public boolean addAdmin(Admin admin);
	
	public List<Admin> getAdmins();

	public Admin getAdmin(String username) throws UserNotFoundException;
	
	public boolean addNewMovie(Movie movie);
}
