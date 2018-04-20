package com.revature.factory;

public class PokemonTrainer {

	public static void main(String[] args) {

		try {
			Pokemon charmander = PokemonFactory.getPokemon("CHarMAndER");
			charmander.primaryMove();
			System.out.println(charmander.getClass().getName());
			
			Pokemon squirtle = PokemonFactory.getPokemon("SQuIRTLE");
			squirtle.primaryMove();
			System.out.println(squirtle.getClass().getName());
			
			Pokemon pikachu = PokemonFactory.getPokemon("PIKACHU");
			pikachu.primaryMove();
			System.out.println(pikachu.getClass().getName());

			Pokemon bulbasaur = PokemonFactory.getPokemon("bulbasaur");
			bulbasaur.primaryMove();
			System.out.println(bulbasaur.getClass().getName());

			Pokemon michu = PokemonFactory.getPokemon("michu");
			
		} catch (PokemonNotFoundException pnfe) {
			System.err.println(pnfe.getMessage());
		}
		
		
	}
}
