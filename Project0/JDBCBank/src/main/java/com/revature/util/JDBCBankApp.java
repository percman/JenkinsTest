package com.revature.util;

import com.revature.model.User;
import com.revature.service.UserService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCBankApp {
    private static Scanner input = new Scanner(System.in);
    final static Logger logger = Logger.getLogger(JDBCBankLogger.class);

    public static boolean deleteUser(int userId) {
        return UserService.deleteUser(userId);
    }

    public static void start() {
        Connection conn = ConnectionUtil.getConnection();

        Menu.mainMenu(input);

        try {
            input.close();
            conn.close();
            logger.info("Resources closed!");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            System.err.println("SQL State: " + sqle.getSQLState());
            System.err.println("Error Code: " + sqle.getErrorCode());
        }
    }

    public static void main(String[] args) {

        start();

    }
}


//        Test delete
//        deleteUser(1);
//        for(User u: UserService.getAllUsers()){
//        System.out.println(u.toString());
//        }