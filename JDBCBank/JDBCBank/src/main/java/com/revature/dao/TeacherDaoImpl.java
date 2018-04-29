package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.singletons.ConnectionUtil;
import com.revature.singletons.LogThis;
import com.revature.users.Person;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPasswordHash(Teacher teacher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getUnapprovedStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getUnlockedStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getLockedStudents() {
		// TODO Auto-generated method stub
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
	public boolean unlockStudent(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStudent(String username) {
		// TODO Auto-generated method stub
		return false;
	}


}
