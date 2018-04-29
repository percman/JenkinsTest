package com.revature.users;

import com.revature.exceptions.InvalidUserTypeException;
import com.revature.service.StudentService;

public class UserFactory {
	
	public static void getNewUser(Person person, String type) throws InvalidUserTypeException {
		
		switch(type.toLowerCase()) {
			case "student":
				StudentService.login(person);
				// go to student login
			case "teacher":
				TeacherService.login(person);
				// go to teacher login
			case "principal":
				PrincipalService.loging(person);
				// go to principal login
			default:
				throw new InvalidUserTypeException();	
		}
	}

}
