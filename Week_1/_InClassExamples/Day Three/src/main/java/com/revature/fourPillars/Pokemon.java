package com.revature.fourPillars;

import java.util.HashSet;
import java.util.Set;

public abstract class Pokemon {

	private String name;
	private Set<String> typesStrongAgainst = new HashSet<>();
	private Set<String> typesWeakAgainst = new HashSet<>();
	
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

	public Set<String> getTypesStrongAgainst() {
		return typesStrongAgainst;
	}

	public void setTypesStrongAgainst(Set<String> typesStrongAgainst) {
		this.typesStrongAgainst = typesStrongAgainst;
	}

	public Set<String> getTypesWeakAgainst() {
		return typesWeakAgainst;
	}

	public void setTypesWeakAgainst(Set<String> typesWeakAgainst) {
		this.typesWeakAgainst = typesWeakAgainst;
	}
	
	public void info() {
		this.sayHello();
		this.strongAgainst();
		this.weakAgainst();
		this.primaryMove();
		this.secondaryMove();
	}
	
	
}
