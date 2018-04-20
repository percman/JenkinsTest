package com.revature.factory;

public class PokemonFactory {

	public static Pokemon getPokemon(String pokemonName) throws PokemonNotFoundException {
		
		switch(pokemonName.toLowerCase()) {
		case "bulbasaur":
			return new Bulbasaur();
		case "pikachu":
			return new Pikachu();
		case "charmander":
			return new Charmander();
		case "squirtle":
			return new Squirtle();
		default:
			throw new PokemonNotFoundException("You don't have a " + pokemonName + ", dude!");
		}
	}
}
