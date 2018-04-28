package com.revature.dao.users;

import java.util.List;

import com.revature.dao.movie.Movie;
import com.revature.exceptions.UserNotFoundException;
import com.revature.users.Admin;

public class AdminService {
	private static AdminDao dao = AdminDaoImpl.getInstance();

	public static boolean addAdmin(Admin admin) {
		return dao.addAdmin(admin);
	}
	
	public static List<Admin> getAdmins(){
		return dao.getAdmins();
	}
	public static Admin getAdmin(String username) throws UserNotFoundException {
		return dao.getAdmin(username);
	}
	public static boolean addNewMovie(Movie movie) {
		return dao.addNewMovie(movie);
	}
	
	public static void main(String[] args) throws UserNotFoundException {
		System.out.println(getAdmin("gary").getPassword());
		for(Admin admin : getAdmins()) {
			System.out.println(admin.getUsername());
		}
	}
	
}
