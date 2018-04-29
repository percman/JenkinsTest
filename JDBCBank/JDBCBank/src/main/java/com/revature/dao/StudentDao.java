package com.revature.dao;

import com.revature.users.Student;

public interface StudentDao {

	Student getStudent(String username);
	boolean insertStudent(Student student);
	boolean updateStudent(Student student);
	String getPasswordHash(Student student);
		
	boolean deleteStudent(String username);

}
