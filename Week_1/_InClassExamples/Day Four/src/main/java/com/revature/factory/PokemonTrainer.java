package com.revature.factory;

public class PokemonTrainer {

	public static void main(String... args) {
		try {
			Pokemon charmander = PokemonFactory.getPokemon("CHarMaNDer");
			charmander.primaryMove();
			System.out.println(charmander.getClass().getName());
			
			Pokemon squirtle = PokemonFactory.getPokemon("SQUIRtle");
			squirtle.primaryMove();
			System.out.println(squirtle.getClass().getName());
			
			Pokemon bulbasaur = PokemonFactory.getPokemon("BULBASAUR");
			bulbasaur.primaryMove();
			System.out.println(bulbasaur.getClass().getName());
			
			Pokemon pikachu = PokemonFactory.getPokemon("pikachu");
			pikachu.primaryMove();
			System.out.println(pikachu.getClass().getName());
			
			Pokemon mssigno = PokemonFactory.getPokemon("mssigno");
		} catch (PokemonNotFoundException pnfe) {
			System.err.println(pnfe.getMessage());
		}
	}
}
