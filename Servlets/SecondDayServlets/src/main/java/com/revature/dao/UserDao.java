package com.revature.dao;

import java.util.List;

import com.revature.model.Pokemon;
import com.revature.model.User;

public interface UserDao {

	List<User> getAllUsers();
	User getUser(String username);
	public boolean insertUser(User user);
	abstract boolean updateUser(User user);
	public abstract boolean deleteUser(String username);
	public abstract String getPasswordHash(User user);
	boolean addPokemon(String pokemonName, String username);
	List<Pokemon> getAllPokemon(String username);
}
