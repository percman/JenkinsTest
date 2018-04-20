package com.revature.comparing;

import java.util.Set;
import java.util.TreeSet;

public class Human {

	private int id;
	private String name;

	public Human(int id, String name) {
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
		return "Human [id=" + id + ", name=" + name + "]";
	}

	public static void main(String[] args) {
		 Set<Human> humans = new TreeSet<>(new HumanHelper());
		// Set<Human> humans = new TreeSet<>((h1, h2) -> h2.getName().compareTo(h1.getName()));
		
		humans.add(new Human(1, "Amelia"));
		humans.add(new Human(18, "Andria"));
		humans.add(new Human(26, "Linda"));
		humans.add(new Human(20, "Chad"));
		
		for(Human h : humans) {
			System.out.println(h);
		}

	}

}
