package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.singletons.ConnectionUtil;
import com.revature.singletons.LogThis;
import com.revature.users.Person;
import com.revature.users.Student;

public class StudentDaoImpl implements StudentDao {

	private static StudentDaoImpl instance;
	
	private StudentDaoImpl() {}
	
	public static StudentDaoImpl getInstance() {
		if (instance == null) {
			instance = new StudentDaoImpl();
		}
		return instance;
	}
	
	
	@Override
	public Person getStudent(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("Select * FROM student WHERE username = ? ");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Student(rs.getString("s_firstname"), rs.getString("s_lastname"), rs.getString("s_username"), rs.getString("password"), 
						rs.getInt("s_coins"), rs.getBoolean("s_bought_sub"), rs.getBoolean("s_bought_mult"), rs.getBoolean("s_bought_div"));
			}
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertStudent(Student student) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_student(?, ?, ?, ?)}");
			stmt.setString(++index, student.getUsername());
			stmt.setString(++index, student.getPassword());
			stmt.setString(++index, student.getFirstname());
			stmt.setString(++index, student.getLastname());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean updateStudent(Student student) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public String getPasswordHash(Student student) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?, ?) AS HASH FROM dual");
			stmt.setString(++index, student.getUsername());
			stmt.setString(++index, student.getPassword());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
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
	public boolean earnCoin(Student student) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean spendCoin(Student student, int cost) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean boughtSubtraction(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean boughtMultiplication(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean boughtDivision(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
