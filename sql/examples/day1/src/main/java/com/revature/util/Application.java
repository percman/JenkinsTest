package com.revature.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.model.Pokemon;
import com.revature.service.PokemonService;

public class Application {

	public static void main(String[] args) {
		System.out.println("Initial size of pokemon in DB: " + PokemonService.getAllPokemon().size());
		
		// Arbitrary id, doesnt matter because it is handled in the insert_pokemon stored procedure 
		Pokemon pokemon = new Pokemon(1234098, "Test", "Pokemon");
		System.out.println("Was the pokemon inserted? " + PokemonService.insertPokemon(pokemon));
		
		System.out.println("Current size of pokemon in DB: " + PokemonService.getAllPokemon().size());
		Pokemon getPokemon = PokemonService.getPokemon("Test");
		System.out.println("Pokemon that was retrieved => " + getPokemon);
		
		getPokemon.setName("Updated");
		getPokemon.setType("Updated");
		
		System.out.println("Was the pokemon updated? " + PokemonService.updatePokemon(getPokemon));
		
		System.out.println("Was the pokemon deleted? " + PokemonService.deletePokemon(getPokemon.getId()));
		
		System.out.println("Final size of pokemon in DB: " + PokemonService.getAllPokemon().size());
		
	}
	
	
	
	
	
	
	
	
	public static void selectAllPokemon() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pokemon");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("id") + ": " + rs.getString("name") + " (" + rs.getString("type") + ")");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
	}
	
	public static boolean insertPokemon(int id, String name, String type) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO pokemon VALUES (?, ?, ?)");
			stmt.setInt(++index, id);
			stmt.setString(++index, name);
			stmt.setString(++index, type);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Pokemon successfully inserted? " + (rowsAffected > 0));
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}
	
	
	public static void selectFromCustomer() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT firstname, customerrid, lastname FROM customer");
			while(rs.next()) {
				System.out.println(rs.getInt(2)+":"+rs.getString(3) + ", " + rs.getString("firstname"));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		} finally {
			try {
				rs.close();
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State: " + sqle.getSQLState());
				System.err.println("Error Code: " + sqle.getErrorCode());
			}
			try {
				stmt.close();
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State: " + sqle.getSQLState());
				System.err.println("Error Code: " + sqle.getErrorCode());
			}
			try {
				conn.close();
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State: " + sqle.getSQLState());
				System.err.println("Error Code: " + sqle.getErrorCode());
			}
		}
	}
}
