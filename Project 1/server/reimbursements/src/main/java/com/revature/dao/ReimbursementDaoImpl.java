package com.revature.dao;

import com.revature.model.CreateReimbursementModel;
import com.revature.model.MyReimbursementReturn;
import com.revature.model.ReimbursementTable;
import com.revature.util.ConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class ReimbursementDaoImpl implements ReimbursementDao{

    private static ReimbursementDaoImpl instance;
    private Logger logger = Logger.getLogger(ReimbursementDaoImpl.class);

    private ReimbursementDaoImpl() {}

    public static ReimbursementDaoImpl getInstance() {
        if (instance == null) {
            instance = new ReimbursementDaoImpl();
        }
        return instance;
    }


    @Override
    public List<MyReimbursementReturn> getRequestedReimbursementsByUser(String username) {
        List<MyReimbursementReturn> ls = new LinkedList<>();

        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT amount a, category c, status s," +
                    "ei.FIRST_NAME f, ei.LAST_NAME l " +
                    "FROM REIMBURSEMENT rei, EMPLOYEE e, EINFO ei " +
                    "WHERE rei.requester = e.EID " +
                    "AND rei.approver = ei.eid " +
                    "AND e.USERNAME = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ls.add(new MyReimbursementReturn(rs.getDouble("a"), rs.getInt("c"),
                    rs.getInt("s"), rs.getString("f"), rs.getString("l")));

            }
            return ls;

        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error("SQL STATE: " + e.getSQLState());
            logger.error("ERROR CODE: " + e.getErrorCode());
        }
        return null;
    }


    @Override
    public boolean createReimbursement(CreateReimbursementModel cm) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL add_reimbursement(?, ?, ?, ?)}");
            stmt.setString(1, cm.getUsername());
            stmt.setString(2, cm.getPassword());
            stmt.setInt(3, cm.getCategory());
            stmt.setDouble(4, cm.getAmount());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error("SQL STATE: " + e.getSQLState());
            logger.error("ERROR CODE: " + e.getErrorCode());
        }
        return false;
    }

    public List<ReimbursementTable> getAllReimbursements() {
        List<ReimbursementTable> ls = new LinkedList<>();

        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT amount a, category c, status s," +
                    "ei.FIRST_NAME fApp, ei.LAST_NAME lApp, eie.FIRST_NAME fReq, eie.LAST_NAME lReq, rei.rid id " +
                    "FROM REIMBURSEMENT rei, EINFO eie, EINFO ei " +
                    "WHERE rei.requester = eie.EID " +
                    "AND rei.approver = ei.eid ");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ls.add(new ReimbursementTable(rs.getDouble("a"), rs.getInt("c"),
                        rs.getInt("s"), rs.getString("fApp"), rs.getString("lApp"),
                        rs.getString("fReq"), rs.getString("lReq"), rs.getInt("id")));

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