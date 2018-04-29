package com.revature.service;

import com.revature.dao.TeacherDao;
import com.revature.dao.TeacherDaoImpl;
import com.revature.users.Student;
import com.revature.users.Teacher;

public class TeacherService {
	
	private static TeacherDao dao = TeacherDaoImpl.getInstance();
	
	private TeacherService() {}
	
//	List<Student> getAllStudents();
//	List<Student> getUnapprovedStudents();
//	List<Student> getUnlockedStudents();
//	List<Student> getLockedStudents();
//	
//	boolean approveAllStudents();
//	boolean approveStudent(String username);
//	boolean lockStudent(String username);
//	boolean unlockStudent(String username);
//	boolean deleteStudent(String username);
	
	public static Teacher getTeacher(String username) {
		return dao.getTeacher(username);
	}
	
	public static boolean insertTeacher(Teacher teacher) {
		return dao.insertTeacher(teacher);
	}
	
	public static boolean updateTeacher(Teacher teacher) {
		return dao.updateTeacher(teacher);
	}
	
	public static Teacher login(Teacher teacher) {
		Teacher temp = dao.getTeacher(teacher.getUsername());
		if (dao.getTeacher(teacher.getUsername()) == null) {
			System.err.println("NOT A VALID USERNAME");
			return null;
		}
		if (temp.getPassword().equals(dao.getPasswordHash(teacher))) {
			System.out.println("You are a valid user, " + temp.getUsername());
			return temp;
		}
		System.err.println("YOU ARE NOT A VALID USER, " + teacher.getUsername());
		return null;
	}
	
	
	

}
