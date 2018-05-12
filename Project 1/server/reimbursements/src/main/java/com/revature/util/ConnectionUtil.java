package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Taken from an in-lecture example.
public class ConnectionUtil {
    private ConnectionUtil() {}

    public static Connection getConnection() {
        InputStream in = null;
        Properties props = new Properties();
        try {
            in = new FileInputStream("src/main/resources/db.properties");
            props.load(in);
            return DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}