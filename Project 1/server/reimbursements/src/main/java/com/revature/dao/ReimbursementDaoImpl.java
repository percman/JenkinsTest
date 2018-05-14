package com.revature.dao;

import com.revature.model.CreateReimbursementModel;
import com.revature.model.ReimbursementTable;
import com.revature.util.ConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


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
    public ReimbursementTable getAllReimbursements() {
        return null;
    }

    @Override
    public ReimbursementTable getRequestedReimbursementsByUser(int id) {
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
}