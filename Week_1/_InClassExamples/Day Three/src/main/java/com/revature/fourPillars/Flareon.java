package com.revature.fourPillars;

public class Flareon extends FirePokemon {

	public Flareon() {
		super();
	}
	
	public Flareon(String name) {
		super(name);
	}

	@Override
	public void primaryMove() {
		System.out.println(this.getName() + " uses Flame Wheel!");
	}

	@Override
	public void secondaryMove() {
		System.out.println(this.getName() + " uses Agility!");
	}
}
