package com.revature.hs.user.dao;

import com.revature.hs.card.Card;
import com.revature.hs.user.Admin;
import com.revature.hs.user.Player;
import com.revature.hs.user.User;
import com.revature.hs.util.ConnectionUtil;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao {
	@Override
	public List<Card> getUserCards(int id) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL STATE: " + e.getSQLState());
			System.err.println("ERROR CODE: " + e.getErrorCode());
		}
		return null;
	}

	@Override
	public User getUser(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL STATE: " + e.getSQLState());
			System.err.println("ERROR CODE: " + e.getErrorCode());
		}
		return null;
	}

	@Override
	public void updateAdmin(Admin admin) {
		updateUser(admin);
	}

	private void updateUser(User user) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE hs_user set username=?," +
					" password=?, is_locked=? WHERE id=?");
			stmt.setString(++index, user.getUserName());
			stmt.setString(++index, user.get)
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL STATE: " + e.getSQLState());
			System.err.println("ERROR CODE: " + e.getErrorCode());
		}
	}

	@Override
	public void updatePlayer(Player player) {
		updateUser(player);
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL STATE: " + e.getSQLState());
			System.err.println("ERROR CODE: " + e.getErrorCode());
		}
	}

	@Override
	public boolean isUser(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT is_user(?) FROM DUAL");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) == 1;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL STATE: " + e.getSQLState());
			System.err.println("ERROR CODE: " + e.getErrorCode());
		}
	}
}
