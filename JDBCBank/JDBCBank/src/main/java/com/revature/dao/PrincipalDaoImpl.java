package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.singletons.ConnectionUtil;
import com.revature.singletons.LogThis;
import com.revature.users.Principal;
import com.revature.users.Teacher;

public class PrincipalDaoImpl implements PrincipalDao{

	private static PrincipalDaoImpl instance;
	
	private PrincipalDaoImpl() {}
	
	public static PrincipalDaoImpl getInstance() {
		if (instance == null) {
			instance = new PrincipalDaoImpl();
		}
		return instance;
	}

	
	
	@Override
	public Principal getPrincipal(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Select * FROM principal WHERE p_username = ? ");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Principal(rs.getString("s_firstname"), rs.getString("s_lastname"), rs.getString("s_username"),
						rs.getString("s_password"));
			}
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertPrincipal(Principal principal) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_principal (?, ?, ?, ?)}");
			stmt.setString(++index, principal.getUsername());
			stmt.setString(++index, principal.getPassword());
			stmt.setString(++index, principal.getFirstname());
			stmt.setString(++index, principal.getLastname());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean updatePrincipal(Principal principal) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL update_principal (?, ?, ?, ?)}");
			stmt.setString(++index, principal.getUsername());
			stmt.setString(++index, principal.getPassword());
			stmt.setString(++index, principal.getFirstname());
			stmt.setString(++index, principal.getLastname());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public String getPasswordHash(Principal principal) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?, ?) AS HASH FROM dual");
			stmt.setString(++index, principal.getUsername());
			stmt.setString(++index, principal.getPassword());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("HASH");
			}
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public List<Teacher> getAllTeachers() {
		List<Teacher> list = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Select * FROM teacher");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Teacher(rs.getString("t_firstname"), rs.getString("t_lastname"), rs.getString("t_username")));
			}
			return list;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public List<Teacher> getUnapprovedTeachers() {
		List<Teacher> list = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Select * FROM teacher WHERE t_approved = 0 ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Teacher(rs.getString("t_firstname"), rs.getString("t_lastname"), rs.getString("t_username")));
			}
			return list;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public List<Teacher> getUnlockedTeachers() {
		List<Teacher> list = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Select * FROM teacher WHERE t_locked = 0 ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Teacher(rs.getString("t_firstname"), rs.getString("t_lastname"), rs.getString("t_username")));
			}
			return list;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public List<Teacher> getLockedTeachers() {
		List<Teacher> list = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Select * FROM teacher WHERE t_locked = 1 ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Teacher(rs.getString("t_firstname"), rs.getString("t_lastname"), rs.getString("t_username")));
			}
			return list;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean approveAllTeachers() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Update teacher SET t_approved = 1 ");
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean approveTeacher(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Update teacher SET t_approved = 1 WHERE t_username = ?");
			stmt.setString(++index, username);
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean lockTeacher(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Update teacher SET t_locked = 1 WHERE t_username = ?");
			stmt.setString(++index, username);
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean unlockAllTeacher() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Update teacher SET t_locked = 0 ");
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean unlockTeacher(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Update teacher SET t_locked = 0 WHERE t_username = ?");
			stmt.setString(++index, username);
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean deleteTeacher(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM teacher WHERE t_username = ? ");
			stmt.setString(++index, username);
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

}
