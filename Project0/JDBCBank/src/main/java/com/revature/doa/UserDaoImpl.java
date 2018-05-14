package com.revature.doa;

import com.revature.exceptions.UserNotFoundException;
import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User getUser(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            User user = new User();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM JDBCUSER WHERE USERID = " + userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user.setUserId(rs.getInt("userId"));
                user.setUserLock(rs.getInt("userLock"));
                user.setUsername(rs.getString("username"));
                user.setUserpass(rs.getString("userpass"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setUserClassId(rs.getInt("classId"));
                user.setCurrentLocationId(rs.getInt("currentLocationId"));
                user.setHP(rs.getInt("HP"));
                user.setLVL(rs.getInt("LVL"));
                user.setEXP(rs.getInt("EXP"));
                user.setATK(rs.getInt("ATK"));
                user.setDEF(rs.getInt("DEF"));
                user.setGold(rs.getDouble("gold"));
                return user;
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            System.err.println("SQL State: " + sqle.getSQLState());
            System.err.println("Error Code: " + sqle.getErrorCode());
        }
        return null;
    }

    @Override
    public User getUser(String username) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            User user = new User();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM JDBCUSER WHERE USERNAME = '" + username + "'");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user.setUserId(rs.getInt("userId"));
                user.setUserLock(rs.getInt("userLock"));
                user.setUsername(rs.getString("username"));
                user.setUserpass(rs.getString("userpass"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setUserClassId(rs.getInt("classId"));
                user.setCurrentLocationId(rs.getInt("currentLocationId"));
                user.setHP(rs.getInt("HP"));
                user.setLVL(rs.getInt("LVL"));
                user.setEXP(rs.getInt("EXP"));
                user.setATK(rs.getInt("ATK"));
                user.setDEF(rs.getInt("DEF"));
                user.setGold(rs.getDouble("gold"));
                return user;
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            System.err.println("SQL State: " + sqle.getSQLState());
            System.err.println("Error Code: " + sqle.getErrorCode());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            List<User> users = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM JDBCUSER ORDER BY userId");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("userId"), rs.getInt("userLock"), rs.getString("username"), rs.getString("userpass"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("classId"), rs.getInt("currentLocationId"), rs.getInt("HP"), rs.getInt("LVL"), rs.getInt("EXP"), rs.getInt("ATK"), rs.getInt("DEF"), rs.getInt("gold")));
            }
            return users;

        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            System.err.println("SQL State: " + sqle.getSQLState());
            System.err.println("Error Code: " + sqle.getErrorCode());
        }
        return null;
    }

    @Override
    public boolean insertUser(User user) {
        int index = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            // Check if user exists. If so return false.
            if (UserService.getUser(user.getUsername()) != null) {
                return false;
            } else {
                CallableStatement stmt = conn.prepareCall("{CALL insertJDBCUser(?,?,?,?)}");
                stmt.setString(++index, user.getUsername());
                stmt.setString(++index, user.getUserpass());
                stmt.setString(++index, user.getFirstName());
                stmt.setString(++index, user.getLastName());
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            System.err.println("SQL State: " + sqle.getSQLState());
            System.err.println("Error Code: " + sqle.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        int index = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            // Check if doesn't exists. If so return false.
            if (UserService.getUser(user.getUsername()) == null) {
                return false;
            } else {
                CallableStatement stmt = conn.prepareCall("{CALL updateJDBCUser(?,?,?,?)}");
                stmt.setInt(++index, user.getUserId());
                stmt.setString(++index, user.getFirstName());
                stmt.setString(++index, user.getLastName());
                stmt.setInt(++index, user.getUserLock());
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            System.err.println("SQL State: " + sqle.getSQLState());
            System.err.println("Error Code: " + sqle.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        int index = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM JDBCUSER WHERE userId = ?");
            stmt.setInt(++index, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            System.err.println("SQL State: " + sqle.getSQLState());
            System.err.println("Error Code: " + sqle.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean login(String username, String userpass) {
        int index = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT count(userId) as match FROM JDBCUser WHERE username ='" + username + "' AND userpass = GET_USER_HASH('" + username + "','" + userpass + "')");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt("match") == 1) {
                    return true;
                }
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            System.err.println("SQL State: " + sqle.getSQLState());
            System.err.println("Error Code: " + sqle.getErrorCode());
        }
        return false;
    }
}
