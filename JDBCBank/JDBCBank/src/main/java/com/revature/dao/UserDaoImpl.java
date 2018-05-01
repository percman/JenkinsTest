package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.logstatus.LogHere;
import com.revature.users.User;
import com.revature.util.ConnectionUtil;


public class UserDaoImpl implements UserDao{

	
	private static UserDaoImpl instance;
	private UserDaoImpl() {}
	
	public static UserDaoImpl getInstance() {
		if(instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<User> getAllUsers() {
		List<User> userlist = new ArrayList<>();
//		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usertable ORDER BY id");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {				
				userlist.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), 
						(rs.getInt("lockstatus")>0), rs.getDouble("balance"), (rs.getInt("adminstatus")>0)));
			}
			return userlist;	
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return null;		
	}
	
	@Override
	public User getAnyUser() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("Testing the connection. Please wait.");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usertable");
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {			
				System.out.println("Success!");
				return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), 
						(rs.getInt("lockstatus")>0), rs.getDouble("balance"), (rs.getInt("adminstatus")>0));
			}
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return null;	
	}

	@Override
	public User getUser(String username) {		
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usertable WHERE username = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {				
				return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), 
						(rs.getInt("lockstatus")>0), rs.getDouble("balance"), (rs.getInt("adminstatus")>0));
			}
				
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_user(?, ?, ?)}");
			stmt.setString(++index, user.getUsername());
			stmt.setString(++index, user.getPassword());
			stmt.setDouble(++index, user.getBalance());
			return stmt.executeUpdate() > 0;
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			int locked;
			if(user.isLocked())
				locked = 1;
			else
				locked = 0;
			int admined;
			if(user.isAdminstatus())
				admined = 1;
			else
				admined = 0;
			
			CallableStatement stmt = conn.prepareCall("{CALL update_user(?, ?, ?, ?, ?)}");
			stmt.setString(++index, user.getUsername());
			stmt.setString(++index, user.getPassword());
			stmt.setInt(++index, locked);
			stmt.setInt(++index, admined);
			stmt.setDouble(++index, user.getBalance());
			return stmt.executeUpdate() > 0;
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return false;
	}

	@Override
	public boolean deleteUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String getPasswordHash(User user) {
		int index = 0; 
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?,?) AS HASH FROM dual");
			stmt.setString(++index, user.getUsername());
			stmt.setString(++index, user.getPassword());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getString("HASH");
			}
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertAdmin(User user) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_admin(?, ?, ?)}");
			stmt.setString(++index, user.getUsername());
			stmt.setString(++index, user.getPassword());
			stmt.setDouble(++index, user.getBalance());
			return stmt.executeUpdate() > 0;
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return false;
	}
	
	@Override
	public Timestamp getUserTime(User user) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT time FROM userrecord WHERE username = ?");
			stmt.setString(++index, user.getUsername());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getTimestamp(1);
			}
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} catch (NullPointerException npe) {
			LogHere.warn(npe.getMessage());
			System.out.println("You have entered an invalid user");
		}
		return null;
	}

	@Override
	public boolean generateUserInterest(User user) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT time FROM userrecord WHERE username = ?");
			stmt.setString(++index, user.getUsername());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getTime(0));
				return true;
			}
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return false;
	}
	
	@Override
	public boolean insertNewRecordedTime() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("Testing the connection. Please wait.");
			CallableStatement stmt = conn.prepareCall("{CALL insert_time}");
			return stmt.executeUpdate() > 0;
			}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return false;	
	}

	@Override
	public Timestamp getLastRecordedTime() {
//		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT time FROM interestrecord ORDER BY id DESC");
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getTimestamp(1);
			}
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} catch (NullPointerException npe) {
			LogHere.warn(npe.getMessage());
			System.out.println("You have entered an invalid user");
		}
		return null;
	}


	
}
