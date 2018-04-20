package com.revatrue.fourPillars;

public class Charmander extends FirePokemon {

	public Charmander() {
		super();
	}
	
	public Charmander(String name) {
		super(name);
	}
	
	@Override
	public void primaryMove() {
		System.out.println(this.getName() + " uses Ember!");
	}

	@Override
	public void secondaryMove() {
		System.out.println(this.getName() + " uses Tail Whip!");
	}
	
	
}
