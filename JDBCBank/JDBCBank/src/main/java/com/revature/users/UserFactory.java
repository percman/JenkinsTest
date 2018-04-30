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
			case "teacher":
				return TeacherService.login((Teacher) person);
			case "principal":
				return PrincipalService.login((Principal) person);
			default:
				throw new InvalidUserTypeException();	
		}
	}

}
