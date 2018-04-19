package com.revature.fourPillars;

import java.util.ArrayList;
import java.util.List;

public class PokemonTrainer {

	public static void main(String[] args) {
		List<FirePokemon> myPokemon = new ArrayList<>();
		myPokemon.add(new Charmander("char"));
		myPokemon.add(new Charmander());
		myPokemon.add(new Magmar("mag"));
		myPokemon.add(new Flareon("Eevee's Best Evolution"));
		
		for (Pokemon p : myPokemon) {
			p.info();
			System.out.println();
		}
	}
}
