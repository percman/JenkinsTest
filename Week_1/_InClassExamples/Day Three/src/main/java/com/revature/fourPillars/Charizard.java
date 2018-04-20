package com.revature.fourPillars;

public class Charizard extends FireFlyingPokemon {

	public Charizard() {
		super();
	}
	
	public Charizard(String name) {
		super(name);
	}
	
	@Override
	public void primaryMove() {
		System.out.println(this.getName() + " uses Fire Blast!");
	}

	@Override
	public void secondaryMove() {
		System.out.println(this.getName() + " uses Fly!");
	}

	
}
