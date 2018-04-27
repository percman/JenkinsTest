package com.revature.application;

import com.revature.model.Pokemon;
import com.revature.service.UserService;

public class App {

	public static void main(String[] args) {
//		User user = new User("username", "password", "Test", "User");
//		System.out.println("User successfully inserted? " + UserService.insertUser(user));
		
//		User user = UserService.getUser("username");
//		System.out.println("Username: " + user.getUsername());
//		System.out.println("Password: " + user.getPassword());
//		System.out.println("Result when hashing: " + UserDaoImpl.getInstance().getPasswordHash(new User("username", "password")));
//		System.out.println("Are they the same? " + (user.getPassword().equals(UserDaoImpl.getInstance()
//				.getPasswordHash(new User("username", "password")))));
		
//		UserService.login(new User("username", "password"));
		
//		Pokemon pokemon = new Pokemon("Charmander", "Fire");
//		Pokemon pokemon = new Pokemon("Squirtle", "Water");
//		Pokemon pokemon = new Pokemon("Bulbasaur", "Grass");
//		Pokemon pokemon = new Pokemon("Pikachu", "Electric");
//		System.out.println("Pokemon successfully inserted? " + PokemonService.insertPokemon(pokemon));
		
//		System.out.println("Was the pokemon successfully added to the user? " + UserService.addPokemon("Bulbasaur", "username"));
		
		for(Pokemon p : UserService.getAllPokemon("username")) {
			System.out.println(p);
		}
		
	}
}
