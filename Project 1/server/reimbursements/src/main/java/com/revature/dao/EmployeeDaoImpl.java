package com.revature.dao;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class EmployeeDaoImpl implements EmployeeDao{
    private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);


    @Override
    public Employee getEmployee(String username) {
        int index = 0;

        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT e.EID, FIRST_NAME, LAST_NAME, ADDRESS " +
                    "FROM employee e, einfo ei WHERE username = ? AND e.EID = ei.EID");
            stmt.setString(++index, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(username, rs.getInt("EID"), rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME"), rs.getString("ADDRESS"),
                        isManager(rs.getInt("EID")));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error("SQL STATE: " + e.getSQLState());
            logger.error("ERROR CODE: " + e.getErrorCode());
        }
        return null;
    }

    @Override
    public String hashPassword(String username, String password) {
        int index = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?, ?) hashed from dual");
            stmt.setString(++index, username);
            stmt.setString(++index, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("hashed");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error("SQL STATE: " + e.getSQLState());
            logger.error("ERROR CODE: " + e.getErrorCode());
        }
        return null;
    }

    @Override
    public String getStoredHash(String username) {
        int index = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT PASSWORD FROM EMPLOYEE WHERE username = ?");
            stmt.setString(++index, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("PASSWORD");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error("SQL STATE: " + e.getSQLState());
            logger.error("ERROR CODE: " + e.getErrorCode());
        }
        return null;
    }

    // Do user checking if there's time.
    @Override
    public boolean updateEmployeeInfo(Employee employee) {
        int index = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE EINFO set FIRST_NAME=?," +
                    " LAST_NAME=?, ADDRESS=? WHERE EID=?");
            stmt.setString(++index, employee.getFirstName());
            stmt.setString(++index, employee.getLastName());
            stmt.setString(++index, employee.getAddress());
            stmt.setInt(++index, employee.getEid());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error("SQL STATE: " + e.getSQLState());
            logger.error("ERROR CODE: " + e.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean isManager(int id) {
        int index = 0;

        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) c " +
                    "FROM FMANAGER WHERE eid=?");
            stmt.setInt(++index, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("c") > 0;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error("SQL STATE: " + e.getSQLState());
            logger.error("ERROR CODE: " + e.getErrorCode());
        }
        return false;
    }

    @Override
    public List<String> getEmployeeNames() {
        List<String> ls = new LinkedList<>();

        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT FIRST_NAME f, LAST_NAME l " +
                    "FROM EINFO");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ls.add(rs.getString("l") + ", " + rs.getString("f"));
            }
            return ls;

        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error("SQL STATE: " + e.getSQLState());
            logger.error("ERROR CODE: " + e.getErrorCode());
        }
        return null;
    }
}