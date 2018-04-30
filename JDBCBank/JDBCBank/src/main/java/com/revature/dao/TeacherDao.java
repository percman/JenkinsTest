package com.revature.dao;

import java.util.List;

import com.revature.users.Student;
import com.revature.users.Teacher;

public interface TeacherDao {
	
	Teacher getTeacher(String username);
	boolean insertTeacher(Teacher teacher);
	boolean updateTeacher(Teacher teacher);
	String getPasswordHash(Teacher teacher);

	List<Student> getAllStudents();
	List<Student> getUnapprovedStudents();
	List<Student> getUnlockedStudents();
	List<Student> getLockedStudents();
	
	boolean approveAllStudents();
	boolean approveStudent(String username);
	boolean lockStudent(String username);
	boolean unlockAllStudent();
	boolean unlockStudent(String username);
	boolean deleteStudent(String username);
	
	int getApproved(String username);
	int getLocked(String username);
	
}
