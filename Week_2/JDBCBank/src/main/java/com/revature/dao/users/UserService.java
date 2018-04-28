package com.revature.dao.users;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.users.UserDao;
import com.revature.dao.users.UserDaoImpl;
import com.revature.exceptions.UserNotFoundException;
import com.revature.users.User;

public class UserService {
	private static UserDao dao = UserDaoImpl.getInstance();
	public static boolean addUser(User user) {
		return dao.addUser(user);
	}
	
	public static boolean lockUser(User user) throws UserNotFoundException {
		return dao.lockUser(user);
	}
	
	public static boolean unlockUser(User user) throws UserNotFoundException {
		return dao.unlockUser(user);
	}
	
	public static boolean approveUser(User user) throws UserNotFoundException {
		return dao.approveUser(user);
	}
	public static List<User> getUsers(){
		return dao.getUsers();
	}
	public static boolean isUserUnapproved(User user) throws UserNotFoundException {
		return dao.isUserUnapproved(user);
	}
	public static boolean isUserLocked(User user) throws UserNotFoundException {
		return dao.isUserLocked(user);
	}
	public static User getUser(String user) throws UserNotFoundException{
		return dao.getUser(user);
	}

	public static void main(String[] args) throws UserNotFoundException {
		/*for(User user : getUsers()) {
			System.out.println(user.getUsername());
		}
			System.out.println(getUser("tom").getPassword());
			*/
			
			//System.out.println(unlockUser(new User("bill","password")));;
			//System.out.println(lockUser(new User("bill","password")));
			//System.out.println(approveUser(new User("bill","password")));
			/*System.out.println("-------------------------------------------------");
			System.out.println(isUserLocked(new User("tom","password")));
			System.out.println(isUserLocked(new User("bill","password")));
			System.out.println(isUserUnapproved(new User("bill","password")));*/
	}
}
