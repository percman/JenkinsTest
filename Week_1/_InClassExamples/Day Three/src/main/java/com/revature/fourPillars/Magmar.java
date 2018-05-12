package com.revature.fourPillars;

public class Magmar extends FirePokemon {

	public Magmar() {
		super();
	}
	
	public Magmar(String name) {
		super(name);
	}

	@Override
	public void primaryMove() {
		System.out.println(this.getName() + " uses Flamethrower!");
	}

	@Override
	public void secondaryMove() {
		System.out.println(this.getName() + " uses Smoke Bomb!");
	}
	
	
}
