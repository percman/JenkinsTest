package com.revature.dao;

import java.util.List;

import com.revature.model.Pokemon;
import com.revature.model.User;

public interface PokemonDao {

	boolean insertPokemon(Pokemon pokemon);
	List<User> getUsers(String pokemonName);
}
