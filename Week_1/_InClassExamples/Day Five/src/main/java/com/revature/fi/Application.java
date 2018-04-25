package com.revature.fi;

import java.util.ArrayList;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		Thread t1 = new Thread(() ->  {
			System.out.println("Inside a Thread, created via a lambda expression");
		});
		
		Thread t2 = new Thread(() -> {
			System.out.println("Inside ANOTHER thread, created via a lambda expression");
		});
		
		t1.start();
		t2.start();
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
		
		grades.replaceAll(x -> x*x);
		for (int i : grades) {
			System.out.println(i);
		}
	}
	
	public static void removeIfExample() {
		List<String> pokemon = new ArrayList<>();
		pokemon.add("Eevee");
		pokemon.add("Gengar");
		pokemon.add("Ghengiskhan");
		pokemon.add("Ekans");
		pokemon.add("Abra");
		pokemon.add("Hitmonchan");
		pokemon.add("Jigglypuff");
		pokemon.add("Machop");
		pokemon.add("Wigglytuff");
		System.out.println("Current size: " + pokemon.size());
		for (String p : pokemon) {
			System.out.println(p);
		}
		
		System.out.println("==================");
		
		pokemon.removeIf(p -> p.startsWith("E") || p.endsWith("ff") || p.contains("p"));
		System.out.println("Current size: " + pokemon.size());
		for (String p : pokemon) {
			System.out.println(p);
		}
	}
}
