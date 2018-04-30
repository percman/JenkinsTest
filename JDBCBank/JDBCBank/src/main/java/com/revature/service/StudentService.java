package com.revature.service;

import com.revature.dao.StudentDao;
import com.revature.dao.StudentDaoImpl;
import com.revature.exceptions.InvalidLoginException;
import com.revature.users.Student;

public class StudentService {

		private static StudentDao dao = StudentDaoImpl.getInstance();
		
		private StudentService () {}
		
		public static Student getStudent(String username) {
			return dao.getStudent(username);
		}
		
		public static boolean insertStudent(Student student) {
			return dao.insertStudent(student);
		}
		
		public static boolean updateStudent(Student student) {
			return dao.updateStudent(student);
		}
		
		public static Student login(Student student) throws InvalidLoginException{
			Student temp = dao.getStudent(student.getUsername());
			if (dao.getStudent(student.getUsername()) == null) {
				throw new InvalidLoginException();
			}
			if (temp.getPassword().equals(dao.getPasswordHash(student))) {
				System.out.println("You are a valid user, " + temp.getUsername());
				return temp;
			}
			throw new InvalidLoginException();
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
