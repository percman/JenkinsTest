package com.revature.service;

import java.util.List;

import com.revature.dao.PokemonDao;
import com.revature.dao.PokemonDaoImpl;
import com.revature.model.Pokemon;

public class PokemonService {

	private static PokemonDao dao = new PokemonDaoImpl();
	
	private PokemonService() {}
	
	public static List<Pokemon> getAllPokemon(){
		return dao.getAllPokemon();
	}
	
	public static boolean insertPokemon(Pokemon pokemon) {
		return dao.insertPokemon(pokemon);
	}
	
	public static Pokemon getPokemon(int id) {
		return dao.getPokemon(id);
	}
	
	public static boolean deletePokemon(int id) {
		return dao.deletePokemon(id);
	}
}
