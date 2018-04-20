package com.revatrue.fourPillars;

import java.util.HashSet;
import java.util.Set;

public abstract class Pokemon {

	private String name;
	private Set<String> typeStrongAgainst = new HashSet<>();
	private Set<String> typeWeakAgainst = new HashSet<>();

	public Pokemon() {
		this("Default Pokemon Name");
	}

	public Pokemon(String name) {
		super();
		this.name = name;
	}

	public abstract void primaryMove();
	public abstract void secondaryMove();
	public abstract void sayHello();
	public abstract void strongAgainst();
	public abstract void weakAgainst();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getTypeStrongAgainst() {
		return typeStrongAgainst;
	}

	public void setTypeStrongAgainst(Set<String> typeStrongAgainst) {
		this.typeStrongAgainst = typeStrongAgainst;
	}

	public Set<String> getTypeWeakAgainst() {
		return typeWeakAgainst;
	}

	public void setTypeWeakAgainst(Set<String> typeWeakAgainst) {
		this.typeWeakAgainst = typeWeakAgainst;
	}

	public void info() {
		this.sayHello();
		this.strongAgainst();
		this.weakAgainst();
		this.primaryMove();
		this.secondaryMove();
	}
	
	
}
