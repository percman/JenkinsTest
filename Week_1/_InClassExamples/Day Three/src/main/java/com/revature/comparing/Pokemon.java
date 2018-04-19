package com.revature.comparing;

import java.util.Set;
import java.util.TreeSet;

public class Pokemon implements Comparable<Pokemon> {

	private int id;
	private String name;
	
	public Pokemon(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + "]";
	}


	@Override
	public int compareTo(Pokemon o) {
		// The following will sort id's ascendingly
//		return this.getId() - o.getId();
		
		// The following will sort id's descendingly
//		return o.getId() - this.getId();
		
		// The following will sort the pokemon's names ascendingly
//		return this.getName().compareTo(o.getName());
		
		// The following will sort the pokemon's names descendingly
		return o.getName().compareTo(this.getName());
	}
	
	public static void main(String[] args) {
		Set<Pokemon> myPokemon = new TreeSet<>();
		myPokemon.add(new Pokemon(13, "Charizard"));
		myPokemon.add(new Pokemon(15, "Mewtwo"));
		myPokemon.add(new Pokemon(5, "Seaking"));
		myPokemon.add(new Pokemon(11, "Pidgey"));
		myPokemon.add(new Pokemon(1, "Bulbasaur"));
		
		for (Pokemon p: myPokemon) {
			System.out.println(p);
		}
	}
	
}
