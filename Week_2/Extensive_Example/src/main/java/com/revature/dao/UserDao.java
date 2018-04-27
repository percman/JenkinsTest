package com.revature.dao;

import java.util.List;

import com.revature.model.Pokemon;
import com.revature.model.User;

public interface UserDao {

	List<User> getAllUsers();
	User getUser(String username);
	public boolean insertUser(User user);
	public abstract boolean updateUser(User user);
	boolean deleteUser(String username);
	String getPasswordHash(User user);
	boolean addPokemon(String pokemonName, String username);
	List<Pokemon> getAllPokemon(String username);
	
}
