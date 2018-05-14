package com.revature.doa;

import com.revature.model.User;

import java.util.List;

public interface UserDao {

    User getUser(int userId);

    User getUser(String username);

    List<User> getAllUsers();

    boolean insertUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int userId);

    boolean login(String username, String userpass);


}
