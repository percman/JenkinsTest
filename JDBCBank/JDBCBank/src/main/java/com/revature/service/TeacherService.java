package com.revature.service;

import java.util.List;

import com.revature.dao.TeacherDao;
import com.revature.dao.TeacherDaoImpl;
import com.revature.exceptions.InvalidLoginException;
import com.revature.users.Student;
import com.revature.users.Teacher;

public class TeacherService {
	
	private static TeacherDao dao = TeacherDaoImpl.getInstance();
	
	private TeacherService() {}
		
	public static Teacher getTeacher(String username) {
		return dao.getTeacher(username);
	}
	
	public static boolean insertTeacher(Teacher teacher) {
		return dao.insertTeacher(teacher);
	}
	
	public static boolean updateTeacher(Teacher teacher) {
		return dao.updateTeacher(teacher);
	}
	
	public static Teacher login(Teacher teacher) throws InvalidLoginException{
		Teacher temp = dao.getTeacher(teacher.getUsername());
		if (dao.getTeacher(teacher.getUsername()) == null) {
			throw new InvalidLoginException();
		}
		if (temp.getPassword().equals(dao.getPasswordHash(teacher))) {
			System.out.println("You are a valid user, " + temp.getUsername());
			return temp;
		}
		throw new InvalidLoginException();
	}
	
	public static List<Student> getAllStudents(){
		return dao.getAllStudents();
	}
	
	public static List<Student> getUnapprovedStudents(){
		return dao.getUnapprovedStudents();
	}
	
	public static List<Student> getUnlockedStudents(){
		return dao.getUnlockedStudents();
	}
	
	public static List<Student> getLockedStudents(){
		return dao.getLockedStudents();
	}
	
	public static boolean approveAllStudents() {
		return dao.approveAllStudents();
	}
	
	public static boolean approveStudent(String username) {
		return dao.approveStudent(username);
	}
	
	public static boolean lockStudent(String username) {
		return dao.lockStudent(username);
	}
	
	public static boolean unlockAllStudent() {
		return dao.unlockAllStudent();
	}

	
	public static boolean unlockStudent(String username) {
		return dao.unlockStudent(username);
	}
		
	
	public static boolean deleteStudent(String username) {
		return dao.deleteStudent(username);
	}
	
	public static int getApproved(String username) {
		return dao.getApproved(username);
	}
	
	public static int getLocked(String username) {
		return dao.getLocked(username);
	}

	

}
