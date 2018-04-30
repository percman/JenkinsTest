package dao;

import model.Administrator;
import model.User;

public interface UserDao {
	boolean createUser(User user);
	User readUser(String name);
	boolean deleteUser(User user);
	boolean approveUser(User user, Administrator administrator);
	boolean rejectUser(User user, Administrator administrator);
	boolean transact(User user, int amount);
	boolean lock(User user);
}
