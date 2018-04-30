package com.revature.service;

import com.revature.doa.UserDao;
import com.revature.doa.UserDaoImpl;
import com.revature.model.User;

import java.util.List;


public class UserService {

    private static UserDao dao = new UserDaoImpl();

    private UserService() {
    }

    public static User getUser(int userID) {
        return dao.getUser(userID);
    }

    public static User getUser(String username) {
        return dao.getUser(username);
    }

    public static List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public static boolean insertUser(User user) {
        return dao.insertUser(user);
    }

    public static boolean deleteUser(int userId) {
        return dao.deleteUser(userId);
    }

    public static boolean updateUser(User user) {
        return dao.updateUser(user);
    }

    public static boolean login(String username, String userpass) {
        return dao.login(username, userpass);
    }

}
