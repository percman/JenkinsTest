package com.revature.fi;

import java.util.ArrayList;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			System.out.println("Inside a thread created via a lambda expression");
		});
		
		Thread t2 = new Thread(() -> {
			System.out.println("Inside ANOTHER thread created via a lambda expression");
		});
		
		t1.start();
		t2.start();
		
	}

	public static void removeIfExample() {
		List<String> pokemon = new ArrayList<>();
		pokemon.add("Gengar");
		pokemon.add("Eevee");
		pokemon.add("Pikachu");
		pokemon.add("Jigglypuff");
		pokemon.add("Machamp");
		pokemon.add("Ekans");

		System.out.println("Current size: " + pokemon.size());

		for (String p : pokemon) {
			System.out.println(p);
		}

		System.out.println("=================");

		pokemon.removeIf(p -> p.startsWith("E"));

		System.out.println("Current size: " + pokemon.size());

		for (String p : pokemon) {
			System.out.println(p);

		}
	}

	public static void replaceAllExample() {
		List<Integer> grades = new ArrayList<>();
		grades.add(35);
		grades.add(47);
		grades.add(12);
		grades.add(6);
		grades.add(8);
		grades.add(17);
		grades.add(9);
		grades.add(20);

		grades.replaceAll(x -> x + 5);
		for (int i : grades) {
			System.out.println(i);
		}
	}
}
