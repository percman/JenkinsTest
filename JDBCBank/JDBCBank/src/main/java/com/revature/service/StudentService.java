package com.revature.service;

import com.revature.dao.StudentDao;
import com.revature.dao.StudentDaoImpl;
import com.revature.users.Person;
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
		
		public static Student login(Student student) {
			Student temp = dao.getStudent(student.getUsername());
			if (dao.getStudent(student.getUsername()) == null) {
				System.err.println("NOT A VALID USERNAME");
				return null;
			}
			if (temp.getPassword().equals(dao.getPasswordHash(student))) {
				System.out.println("You are a valid user, " + temp.getUsername());
				return temp;
			}
			System.err.println("YOU ARE NOT A VALID USER, " + student.getUsername());
			return null;
		}
		
		public static boolean deleteStudent(String username) {
			return dao.deleteStudent(username);
		}
	
}
