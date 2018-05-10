package com.revature.dao;

import java.util.List;

import com.revature.model.Pokemon;

public interface PokemonDao {

//	Pokemon getPokemon(String name);
	boolean insertPokemon(Pokemon pokemon);
	List<Pokemon> getAllPokemon();
	
}
