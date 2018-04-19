package com.revature.users;

import java.util.HashMap;

public class Application extends ProjectUsers{

	
	public static void main(String[] args) {	
		
		userFile.delete();
		
		ProjectUsers p = addProjectUserAsAdmin("adamL", true, 53.6);
		ProjectUsers v = addProjectUserAsAdmin("admjl", false, 573.6);
		ProjectUsers f = addProjectUserAsAdmin("sfd2", true, 531.6);
				
		HashMap<Integer, ProjectUsers> userHashData = new HashMap<>();
				
		userHashData = hashSetUserData(userFile);
		
		System.out.println(userHashData);
		System.out.println(userHashData.size());
		
		System.out.println("===========");
		
		System.out.println(adminCheck(userHashData, "sfd2"));
		
		
		
		codeCleanUp();
	}

}