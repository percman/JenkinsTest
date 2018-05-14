package com.revature.doa;

import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.util.ConnectionUtil;

import java.sql.*;

public class BankDaoImpl implements BankDao {

    @Override
    public double getGold(User user) {
        double gold = 0;
        int index = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT gold FROM BANKACCOUNT WHERE USERID = '" + user.getUserId() + "'");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("gold");
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            System.err.println("SQL State: " + sqle.getSQLState());
            System.err.println("Error Code: " + sqle.getErrorCode());
        }
        return 0;
    }

    @Override
    public boolean withdrawBankGold(User user, double withdraw) {
        int index = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL withdrawBankGold(?," + withdraw + ")}");
            stmt.setInt(++index, user.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            System.err.println("SQL State: " + sqle.getSQLState());
            System.err.println("Error Code: " + sqle.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean depositUserGold(User user, double deposit) {
        int index = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL depositUserGold(?," + deposit + ")}");
            stmt.setInt(++index, user.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            System.err.println("SQL State: " + sqle.getSQLState());
            System.err.println("Error Code: " + sqle.getErrorCode());
        }
        return false;
    }
}
