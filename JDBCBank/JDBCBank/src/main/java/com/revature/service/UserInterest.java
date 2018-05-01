package com.revature.service;

import static com.revature.service.UserService.getAllUsers;
import static com.revature.service.UserService.getLastRecordedTime;
import static com.revature.service.UserService.insertNewRecordedTime;
import static com.revature.service.UserService.updateUser;

import java.sql.Timestamp;
import java.util.List;

import com.revature.users.User;

public class UserInterest {

	public static List<User> generateInterest() {
		
    	List<User> priorToInterest = getAllUsers();
    	
		Timestamp lastTime = getLastRecordedTime();
		insertNewRecordedTime();
		Timestamp newTime = getLastRecordedTime();
    	
    	for(User u : priorToInterest) {
//    		System.out.println("Prior to interest: " + u.getBalance());
    		u.setBalance(calculateInterest(u, lastTime, newTime));
//    		System.out.println("After interest: " + u.getBalance());
    		updateUser(u);
    	}
    	
    	return priorToInterest;
		
	}
	
	@SuppressWarnings("unused")
	public static double calculateInterest(User user, Timestamp lastTime, Timestamp newTime) {
		
		double rate = 5;

		double secondsPassed = ((newTime.getTime() - lastTime.getTime())/1000);
		double minPassed = ((newTime.getTime() - lastTime.getTime())/60000);
		double hourPassed = ((newTime.getTime() - lastTime.getTime())/3600000);
		
		double calculatedInterest = user.getBalance() * Math.pow((1 + rate/100),minPassed);
		
		return calculatedInterest;
	}
}
