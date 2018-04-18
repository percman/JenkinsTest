package com.revature.fourPillars;

import java.util.Iterator;

public abstract class FirePokemon extends Pokemon implements Fire {

	private String type = Fire.type;
	
	public FirePokemon() {
		this("Default Pokemon Name");
	}
	
	public FirePokemon(String name) {
		super(name);
	}
	
	{
		// Enhanced for-loop
		// This requires that the right side of the colon implements Iterable
		// And the left side is of the same type
		
		for (String s : weakAgainst) {
			this.getTypesWeakAgainst().add(s);
		}
		
		for (String s : strongAgainst) {
			this.getTypesStrongAgainst().add(s);
		}
	}
	
	public String getType() {
		return this.type;
	}
	
	@Override
	public void sayHello() {
		System.out.println(this.getName() + ", a " + this.getType() + " type, says hello!");
	}
	
	@Override 
	public void strongAgainst() {
		System.out.print(this.getName() + " is strong against ");
		Iterator<String> it = this.getTypesStrongAgainst().iterator();
		while (it.hasNext()) {
			System.out.print(it.next());
			if (it.hasNext()) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
	
	@Override 
	public void weakAgainst() {
		System.out.print(this.getName() + " is weak against ");
		Iterator<String> it = this.getTypesWeakAgainst().iterator();
		while (it.hasNext()) {
			System.out.print(it.next());
			if (it.hasNext()) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
	
	
}
