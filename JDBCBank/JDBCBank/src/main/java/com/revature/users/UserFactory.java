package com.revature.users;

import com.revature.exceptions.InvalidUserTypeException;
import com.revature.service.PrincipalService;
import com.revature.service.StudentService;
import com.revature.service.TeacherService;

public class UserFactory {
	
	public static Person getNewUser(Person person, String type) throws InvalidUserTypeException {
		
		switch(type.toLowerCase()) {
			case "student":
				return StudentService.login((Student) person);
				// go to student login
			case "teacher":
				return TeacherService.login((Teacher) person);
				// go to teacher login
			case "principal":
				return PrincipalService.login((Principal) person);
				// go to principal login
			default:
				throw new InvalidUserTypeException();	
		}
	}

}
