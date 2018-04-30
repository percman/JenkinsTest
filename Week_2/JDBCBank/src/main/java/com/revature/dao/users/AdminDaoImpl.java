package com.revature.dao.users;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.connection.ConnectionUtil;
import com.revature.dao.movie.Movie;
import com.revature.exceptions.UserNotFoundException;
import com.revature.users.Admin;
import com.revature.users.User;

public class AdminDaoImpl implements AdminDao {

private static AdminDaoImpl instance;
	
	private AdminDaoImpl() {}
	
	public static AdminDaoImpl getInstance() {
		if(instance == null) {
			instance = new AdminDaoImpl();
		}
		return instance;
	}
	
	
	@Override
	public boolean addAdmin(Admin admin) {
		// TODO add admin to database
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL insert_admin(?,?)}");
			stmt.setString(++index, admin.getUsername());
			stmt.setString(++index, admin.getPassword());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State:" + sqle.getSQLState());
			System.err.println("SQL Code:" + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public List<Admin> getAdmins() {
		// TODO generates a list of admins from the database
		try(Connection conn = ConnectionUtil.getConnection()){
			List<Admin> admin= new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movie_admin ORDER BY admin_username");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				admin.add(new Admin(rs.getString("admin_username"),rs.getString("admin_password")));
			}
			return admin;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+sqle.getSQLState());
			System.err.println("Error message: "+sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public Admin getAdmin(String username) throws UserNotFoundException {
		// TODO returns the admin with the given object
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movie_admin WHERE admin_username = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return new Admin(rs.getString("admin_username"), rs.getString("admin_password"));
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+sqle.getSQLState());
			System.err.println("Error message: "+sqle.getErrorCode());
		}
		throw new UserNotFoundException();
	}

	@Override
	public boolean addNewMovie(Movie movie){
		// TODO adds a new movie into the database
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL insert_movie(?)}");
			stmt.setString(++index, movie.getTitle());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State:" + sqle.getSQLState());
			System.err.println("SQL Code:" + sqle.getErrorCode());
		}
		return false;
	}
	
	@Override
	public String getPasswordHash(Admin admin) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?,?)AS HASH FROM dual");
			stmt.setString(++index, admin.getUsername());
			stmt.setString(++index, admin.getPassword());
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return rs.getString("HASH");
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State:" + sqle.getSQLState());
			System.err.println("SQL Code:" + sqle.getErrorCode());
		}
		return null;
	}

}
