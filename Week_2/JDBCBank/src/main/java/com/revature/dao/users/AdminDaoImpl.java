package com.revature.dao.users;

import java.util.ArrayList;

import com.revature.dao.movie.Movie;
import com.revature.users.Admin;

public class AdminDaoImpl implements AdminDao {

private static AdminDaoImpl instance;
	
	private AdminDaoImpl() {}
	
	public static AdminDaoImpl getInstance() {
		if(instance == null) {
			instance = new AdminDaoImpl();
		}
		return instance;
	}
	
	
	@Override
	public boolean addAdmin(Admin admin) {
		// TODO add admin to database
		return false;
	}

	@Override
	public ArrayList<Admin> getAdmins() {
		// TODO generates a list of admins from the database
		return null;
	}

	@Override
	public Admin getAdmin(String username) {
		// TODO returns the admin with the given object
		return null;
	}

	@Override
	public boolean addNewMovie(Movie movie) {
		// TODO adds a new movie into the database
		return false;
	}

}
