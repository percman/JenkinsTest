package com.revature.dao;

import java.util.List;

import com.revature.users.Person;
import com.revature.users.Student;
import com.revature.users.Teacher;

public class TeacherDaoImpl implements TeacherDao {

	@Override
	public Person getTeacher(String username) {
		// TODO Auto-generated method stub
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
