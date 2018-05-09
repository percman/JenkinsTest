package com.revature.service;

import java.util.List;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.model.Pokemon;
import com.revature.model.User;

public class UserService {

	private static UserDao dao = UserDaoImpl.getInstance();
	
	private UserService() {}
	
	public static User getUser(String username) {
		return dao.getUser(username);
	}
	
	public static boolean insertUser(User user) {
		return dao.insertUser(user);
	}
	
	public static User login(User user) {
		User temp = dao.getUser(user.getUsername());
		
		if (temp.getPassword().equals(dao.getPasswordHash(user))) {
			System.out.println("You are a valid user, " + temp.getUsername());
			return temp;
		}
		System.err.println("YOU ARE NOT A VALID USER, " + user.getUsername());
		return null;
	}
	
	public static boolean addPokemon(String pokemonName, String username) {
		return dao.addPokemon(pokemonName, username);
	}
	
	public static List<Pokemon> getAllPokemon(String username) {
		return dao.getAllPokemon(username);
	}
}
