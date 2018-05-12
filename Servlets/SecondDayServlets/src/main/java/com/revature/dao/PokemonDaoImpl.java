package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Pokemon;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class PokemonDaoImpl implements PokemonDao {

	private static PokemonDaoImpl instance;
	
	private PokemonDaoImpl() {}
	
	public static PokemonDaoImpl getInstance() {
		if (instance == null) {
			instance = new PokemonDaoImpl();
		}
		return instance;
	}
	
	@Override
	public boolean insertPokemon(Pokemon pokemon) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_pokemon(?, ?)}");
			stmt.setString(++index, pokemon.getName());
			stmt.setString(++index, pokemon.getType());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public List<User> getUsers(String pokemonName) {
		List<User> users = new ArrayList<>();
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT pu.id, pu.username, pu.password, pu.firstname, pu.lastname FROM junction_table j "
					+ "INNER JOIN pokemon_user pu ON j.user_id = pu.id "
					+ "INNER JOIN pokemon p ON j.pokemon_id = p.id "
					+ "WHERE p.name = ?");
			stmt.setString(++index, pokemonName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname")));
			}
			return users;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}
	
	public List<Pokemon> getAllPokemon() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			List<Pokemon> pokemon = new ArrayList<>();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM pokemon");
			while(rs.next()) {
				pokemon.add(new Pokemon(rs.getInt("id"), rs.getString("name"), rs.getString("type")));
			}
			return pokemon;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		
		return null;
	}

}













