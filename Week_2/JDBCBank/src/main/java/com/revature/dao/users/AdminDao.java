package com.revature.dao.users;

import java.util.List;

import com.revature.dao.movie.Movie;
import com.revature.exceptions.UserNotFoundException;
import com.revature.users.Admin;
import com.revature.users.User;


public interface AdminDao {

	public boolean addAdmin(Admin admin);
	
	public List<Admin> getAdmins();

	public Admin getAdmin(String username) throws UserNotFoundException;
	
	public boolean addNewMovie(Movie movie);

	public String getPasswordHash(Admin admin);
}
