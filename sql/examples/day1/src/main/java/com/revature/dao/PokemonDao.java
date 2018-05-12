package com.revature.dao;

import java.util.List;

import com.revature.model.Pokemon;

public interface PokemonDao {

	public Pokemon getPokemon(String name);
	List<Pokemon> getAllPokemon();
	public boolean insertPokemon(Pokemon pokemon);
	public boolean updatePokemon(Pokemon pokemon);
	boolean deletePokemon(int id);
	
}
