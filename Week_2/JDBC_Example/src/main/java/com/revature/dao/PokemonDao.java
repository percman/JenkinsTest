package com.revature.dao;

import java.util.List;

import com.revature.model.Pokemon;

public interface PokemonDao {

	public Pokemon getPokemon(int id);
	public List<Pokemon> getAllPokemon();
	public boolean insertPokemon(Pokemon pokemon);
	public boolean updatePokemon(Pokemon pokemon);
	public boolean deletePokemon(int id);
	
}
