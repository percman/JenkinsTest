package com.revatrue.fourPillars;

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
		// Requires that the right side of the colon implements Iterable
		// and the left side is of the same type
		for(String s : weakAgainst) {
			this.getTypeWeakAgainst().add(s);
		}
		
		for(String s : strongAgainst) {
			this.getTypeStrongAgainst().add(s);
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
		System.out.print(this.getName() + " is strong again");
		Iterator<String> it = this.getTypeStrongAgainst().iterator();
		while(it.hasNext()) {
			System.out.print(it.next());
			if (it.hasNext()) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
	
	@Override
	public void weakAgainst() {
		System.out.print(this.getName() + " is weak again");
		Iterator<String> it = this.getTypeWeakAgainst().iterator();
		while(it.hasNext()) {
			System.out.print(it.next());
			if (it.hasNext()) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
	
	
	
	
}
