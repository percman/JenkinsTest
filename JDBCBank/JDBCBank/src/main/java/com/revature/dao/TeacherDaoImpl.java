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
import com.revature.users.Student;
import com.revature.users.Teacher;

public class TeacherDaoImpl implements TeacherDao {

	private static TeacherDaoImpl instance;
	
	private TeacherDaoImpl() {}
	
	public static TeacherDaoImpl getInstance() {
		if (instance == null) {
			instance = new TeacherDaoImpl();
		}
		return instance;
	}

	
	@Override
	public Teacher getTeacher(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Select * FROM teacher WHERE t_username = ? ");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Teacher(rs.getString("s_firstname"), rs.getString("s_lastname"), rs.getString("s_username"),
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
	public boolean insertTeacher(Teacher teacher) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_teacher (?, ?, ?, ?)}");
			stmt.setString(++index, teacher.getUsername());
			stmt.setString(++index, teacher.getPassword());
			stmt.setString(++index, teacher.getFirstname());
			stmt.setString(++index, teacher.getLastname());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean updateTeacher(Teacher teacher) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL update_teacher (?, ?, ?, ?, ?, ?, ?, ?)}");
			stmt.setString(++index, teacher.getUsername());
			stmt.setString(++index, teacher.getPassword());
			stmt.setString(++index, teacher.getFirstname());
			stmt.setString(++index, teacher.getLastname());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}
	
	@Override
	public String getPasswordHash(Teacher teacher) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?, ?) AS HASH FROM dual");
			stmt.setString(++index, teacher.getUsername());
			stmt.setString(++index, teacher.getPassword());
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
	public List<Student> getAllStudents() {
		List<Student> list = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Select * FROM student");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Student(rs.getString("s_firstname"), rs.getString("s_lastname"), rs.getString("s_username")));
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
	public List<Student> getUnapprovedStudents() {
		List<Student> list = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Select * FROM student WHERE s_approved = 0 ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Student(rs.getString("s_firstname"), rs.getString("s_lastname"), rs.getString("s_username")));
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
	public List<Student> getUnlockedStudents() {
		List<Student> list = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Select * FROM student WHERE s_locked = 0 ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Student(rs.getString("s_firstname"), rs.getString("s_lastname"), rs.getString("s_username")));
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
	public List<Student> getLockedStudents() {
		List<Student> list = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Select * FROM student WHERE s_locked = 1 ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Student(rs.getString("s_firstname"), rs.getString("s_lastname"), rs.getString("s_username")));
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
	public boolean approveAllStudents() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean approveStudent(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lockStudent(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean unlockAllStudent(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unlockStudent(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean deleteStudent(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM student WHERE s_username = ? ");
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
