package com.revature.menus;

import com.revature.exceptions.InvalidLoginException;
import com.revature.exceptions.InvalidUserTypeException;
import com.revature.service.PrincipalService;
import com.revature.service.StudentService;
import com.revature.service.TeacherService;
import com.revature.singletons.LogThis;
import com.revature.users.Principal;
import com.revature.users.Student;
import com.revature.users.Teacher;

public class LoginFactory {
	
	public static void chooseLogin(String username, String password, String type) throws InvalidUserTypeException {
		
		switch(type.toLowerCase()) {
			case "student":
				try {
				Student student = StudentService.login(new Student(username, password));
				StudentMenu.studentMenu(student);
				return;
				} catch (InvalidLoginException ile) {
					LogThis.warn(ile.getMessage());
					StartMenu.startMenu();
				}
			case "teacher":
				try {
				Teacher teacher = TeacherService.login(new Teacher(username, password));
				TeacherMenu.teacherMenu(teacher);
				return;
				} catch (InvalidLoginException ile) {
					LogThis.warn(ile.getMessage());
					StartMenu.startMenu();
				}
			case "principal":
				try {
				Principal principal = PrincipalService.login(new Principal(username, password));
				PrincipalMenu.principalMenu(principal);
				return;
				} catch (InvalidLoginException ile) {
					LogThis.warn(ile.getMessage());
					StartMenu.startMenu();
				}
			default:
				throw new InvalidUserTypeException();	
		}
	}

}
