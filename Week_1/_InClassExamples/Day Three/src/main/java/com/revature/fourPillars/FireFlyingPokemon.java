package com.revature.fourPillars;

import java.util.Iterator;

public abstract class FireFlyingPokemon extends Pokemon implements Flying, Fire {

	private String type = Fire.type + "/" + Flying.type;
	
	{
		for (String s : Fire.weakAgainst) {
			this.getTypesWeakAgainst().add(s);
		}
		for (String s : Fire.strongAgainst) {
			this.getTypesStrongAgainst().add(s);
		}
		for (String s : Flying.weakAgainst) {
			this.getTypesWeakAgainst().add(s);
		}
		for (String s : Flying.strongAgainst) {
			this.getTypesStrongAgainst().add(s);
		}
	}
	
	public FireFlyingPokemon() {
		super();
	}
	
	public FireFlyingPokemon(String name) {
		super(name);
	}
	
	public String getType() {
		return type;
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
