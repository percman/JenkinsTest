package com.revature.zero.Project_Zero;

import java.util.ArrayList;

public interface UserDao {
	
	public User getUser(String name);
	public ArrayList<User> getAllUsers();
	public boolean insertUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String name);
	public int getTotalUsers();
	public int getTotalBalance();

}
