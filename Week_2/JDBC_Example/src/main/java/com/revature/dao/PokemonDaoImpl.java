package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Pokemon;
import com.revature.util.ConnectionUtil;

public class PokemonDaoImpl implements PokemonDao {

	@Override
	public Pokemon getPokemon(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			Pokemon pokemon = null;
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pokemon WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				pokemon = new Pokemon(rs.getInt("id"), rs.getString("name"), rs.getString("type"));
			}
			return pokemon;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public List<Pokemon> getAllPokemon() {
		try (Connection conn = ConnectionUtil.getConnection()){
			List<Pokemon> pokemon = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pokemon ORDER BY id");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
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

	@Override
	public boolean insertPokemon(Pokemon pokemon) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_pokemon(?, ?)}");
			stmt.setString(++index, pokemon.getName());
			stmt.setString(++index, pokemon.getType());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean updatePokemon(Pokemon pokemon) {
		
		return false;
	}

	@Override
	public boolean deletePokemon(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM pokemon WHERE id = ?");
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

}
