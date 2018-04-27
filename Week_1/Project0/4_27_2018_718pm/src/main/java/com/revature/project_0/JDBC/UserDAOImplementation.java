package com.revature.project_0.JDBC;

import static com.revature.scanner.BankScanner.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.project_0.User;

public class UserDAOImplementation implements UserDAO {

	@Override
	public User getUser(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			User user = new User();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM account WHERE accountnumber = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("accountnumber"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getInt("balance"),
						 rs.getInt("approved"), rs.getBoolean("locked"), rs.getBoolean("admin"));
			}
			return user;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}
	
	@Override
	public User getUser(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			User user = new User();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM account WHERE username = '" + username +"'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("accountnumber"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getInt("balance"),
						 rs.getInt("approved"), rs.getBoolean("locked"), rs.getBoolean("admin"));
			}
			return user;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			List<User> users = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM account");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				users.add(new User(rs.getInt("accountnumber"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getInt("balance"),
						 rs.getInt("approved"), rs.getBoolean("locked"), rs.getBoolean("admin")));
			}
			return users;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL insert_user(?,?,?,?)}");
			stmt.setString(++index, user.getFirstName());
			stmt.setString(++index, user.getLastName());
			stmt.setString(++index, user.getUsername());
			stmt.setString(++index, user.getPassword());
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
	public boolean approveUser(int id) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE account SET approved=1 WHERE accountnumber=" + id);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Was the user successfully approved? " + (rowsAffected > 0));
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State" + sqle.getSQLState());
			System.err.println("Error Code" + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean denyUser(int id) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE account SET approved=-1 WHERE accountnumber=" + id);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Was the user successfully denied? " + (rowsAffected > 0));
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State" + sqle.getSQLState());
			System.err.println("Error Code" + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean lockUser(int id) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE account SET locked=1 WHERE accountnumber=" + id);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Was the user successfully locked? " + (rowsAffected > 0));
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State" + sqle.getSQLState());
			System.err.println("Error Code" + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean unlockUser(int id) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE account SET locked=0 WHERE accountnumber=" + id);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Was the user successfully unlocked? " + (rowsAffected > 0));
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State" + sqle.getSQLState());
			System.err.println("Error Code" + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean deposit(int id, int deposit) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE account SET balance=balance + " + deposit + " WHERE accountnumber=" + id);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Was the deposit successful? " + (rowsAffected > 0));
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State" + sqle.getSQLState());
			System.err.println("Error Code" + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean withdraw(int id, int withdrawal) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE account SET balance=balance - " + withdrawal + " WHERE accountnumber=" + id);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Was the deposit successful? " + (rowsAffected > 0));
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State" + sqle.getSQLState());
			System.err.println("Error Code" + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean deleteUser(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL delete_user(?)}");
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Was the user successfully deleted? " + (rowsAffected > 0));
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State" + sqle.getSQLState());
			System.err.println("Error Code" + sqle.getErrorCode());
		}
		return false;
	}
	
	@Override
	public String getPasswordHash(User user) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?,?) AS HASH FROM dual");
			stmt.setString(++index, user.getUsername());
			stmt.setString(++index, user.getPassword());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("HASH");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean checkForAdmin() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM account WHERE admin = 1");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean promoteAdmin(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL promote_admin(?)}");
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Was the user successfully promoted? " + (rowsAffected > 0));
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}
}
