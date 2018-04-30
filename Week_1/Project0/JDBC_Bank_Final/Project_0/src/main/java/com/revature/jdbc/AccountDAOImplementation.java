package com.revature.jdbc;

import static com.revature.logger.BankLogger.logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.factory.UserInterface;
import com.revature.trade.Trade;
import com.revature.user.User;

/**
 * Implementations for all the methods described in the DAO
 * @author Jesse
 *
 */

public class AccountDAOImplementation implements AccountDAO {

	Connection conn = ConnectionObject.getInstance();
	
	@Override // returns a user by account number
	public User getUser(int id) {
		try {
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
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}
	
	@Override // returns a user by username
	public User getUser(String username) {
		try {
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
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override // returns all the users in the database
	public List<User> getAllUsers() {
		try {
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
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}
	

	@Override // returns all the pending users in the database
	public List<User> getPendingUsers() {
		try {
			List<User> users = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM account WHERE approved = 0");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				users.add(new User(rs.getInt("accountnumber"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getInt("balance"),
						 rs.getInt("approved"), rs.getBoolean("locked"), rs.getBoolean("admin")));
			}
			return users;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override // inserts a user in the database
	public boolean insertUser(User user) {
		int index = 0;
		try {
			CallableStatement stmt = conn.prepareCall("{CALL insert_user(?,?,?,?)}");
			stmt.setString(++index, user.getFirstName());
			stmt.setString(++index, user.getLastName());
			stmt.setString(++index, user.getUsername());
			stmt.setString(++index, user.getPassword());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override // Approves a user in the database
	public boolean approveUser(int id) {
		try {
			CallableStatement stmt = conn.prepareCall("{CALL approve_user(?)}");
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\tAccount approved");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override // Denies a user in the database - not this does not delete them
	public boolean denyUser(int id) {
		try {
			CallableStatement stmt = conn.prepareCall("{CALL deny_user(?)}");
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\tAccount denied");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override // Locks a user in the database
	public boolean lockUser(int id) {
		try {
			CallableStatement stmt = conn.prepareCall("{CALL lock_user(?)}");
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\tAccount Locked");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override // Unlocks a user in the database
	public boolean unlockUser(int id) {
		try {
			CallableStatement stmt = conn.prepareCall("{CALL unlock_user(?)}");
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\tAccount Unlocked");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override // Make a monetary deposit into the database - not currently implemented
	public boolean deposit(int id, int deposit) {
		try {
			PreparedStatement stmt = conn.prepareStatement("UPDATE account SET balance=balance + " + deposit + " WHERE accountnumber=" + id);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Was the deposit successful? " + (rowsAffected > 0));
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override // Make a monetary withdrawal from the database - not currently implemented
	public boolean withdraw(int id, int withdrawal) {
		try {
			PreparedStatement stmt = conn.prepareStatement("UPDATE account SET balance=balance - " + withdrawal + " WHERE accountnumber=" + id);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Was the deposit successful? " + (rowsAffected > 0));
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override // delete a user from the database - will only work on users with no TiCos
	public boolean deleteUser(int id) {
		try {
			CallableStatement stmt = conn.prepareCall("{CALL delete_user(?)}");
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\tAccount Deleted");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}
	
	@Override // Get the hashed password from the database
	public String getPasswordHash(UserInterface user) {
		int index = 0;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?,?) AS HASH FROM dual");
			stmt.setString(++index, user.getUsername());
			stmt.setString(++index, user.getPassword());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("HASH");
			}
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override // Get for an admin in the database
	public boolean checkForAdmin() {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM account WHERE admin = 1");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override // promote a user to admin status
	public boolean promoteAdmin(int id) {
		try {
			CallableStatement stmt = conn.prepareCall("{CALL promote_admin(?)}");
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\tAccount Promoted");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override // change a users status back to pending
	public boolean pendUser(int id) {
		try {
			CallableStatement stmt = conn.prepareCall("{CALL pend_user(?)}");
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\tAccount to pending");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override // Return the total of pending users in the database as a number using a function
	public int getNumberOfPendingUsers() {
		try {
			PreparedStatement stmt = conn.prepareCall("SELECT get_number_of_pending_users FROM dual");
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("get_number_of_pending_users");
			}
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return 0;
	}
}
