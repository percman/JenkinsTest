package com.revature.dao;

import com.revature.users.Person;
import com.revature.users.Student;

public interface StudentDao {

	Person getStudent(String username);
	boolean insertStudent(Student student);
	boolean updateStudent(Student student);
	String getPasswordHash(Student student);
	
	boolean earnCoin(Student student);
	boolean spendCoin(Student student, int cost);
	
	boolean boughtSubtraction(String username);
	boolean boughtMultiplication(String username);
	boolean boughtDivision(String username);
	
	void truncateStudent();

}
