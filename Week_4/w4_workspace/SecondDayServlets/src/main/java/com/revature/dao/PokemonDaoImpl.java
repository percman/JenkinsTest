package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Pokemon;
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
	public List<Pokemon> getAllPokemon() {
		try (Connection conn = ConnectionUtil.getConnection()){
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
