package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.connection.ConnectionUtil;
import com.revature.employee.FinanceManager;
import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.reimbursement.Category;
import com.revature.reimbursement.Reimbursment;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
	private static ReimbursementDaoImpl instance;
		
		private ReimbursementDaoImpl() {}
		
		public static ReimbursementDaoImpl getInstance() {
			if(instance == null) {
				instance = new ReimbursementDaoImpl();
			}
			return instance;
		}

		@Override
		public boolean submitReimbursment(Reimbursment rebur) {
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				CallableStatement stmt = conn.prepareCall("{CALL insert_reimbursement(?,?,?)}");
				stmt.setString(++index, rebur.getCat().toString());
				stmt.setInt(++index, rebur.getAmount());
				stmt.setInt(++index, rebur.getSumbitterId());
				return stmt.executeUpdate() > 0;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			return false;
		}

		@Override
		public boolean approveReimbursment(Reimbursment rebur) {
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				CallableStatement stmt = conn.prepareCall("{CALL update_reimbursement(?,?,?)}");
				stmt.setInt(++index, rebur.getApproverId());
				stmt.setInt(++index, rebur.getReimburseId());
				stmt.setInt(++index, 1);
				return stmt.executeUpdate() > 0;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			return false;
		}

		@Override
		public boolean denyReimbursment(Reimbursment rebur) {
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				CallableStatement stmt = conn.prepareCall("{CALL update_reimbursement(?,?,?)}");
				stmt.setInt(++index, rebur.getApproverId());
				stmt.setInt(++index, rebur.getReimburseId());
				stmt.setInt(++index, -1);
				return stmt.executeUpdate() > 0;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			return false;
		}

		@Override
		public Reimbursment getReimbursmentByName(String emp) throws EmployeeNotFoundException {
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM REIMBURSEMENT WHERE SUBMITTER_ID IN (SELECT EMP_ID FROM EMPLOYEE WHERE EMPLOYEE.EMP_USERNAME = ?)");
				stmt.setString(++index, emp);
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next())
					return new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved"));
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new EmployeeNotFoundException();
		}

		@Override
		public List<Reimbursment> getReimbursemnts() {
			try(Connection conn = ConnectionUtil.getConnection()){
				List<Reimbursment> rebur= new ArrayList<>();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursement ORDER BY rebur_id");
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					rebur.add(new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved")));
				}
				return rebur;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			return null;
		}

		@Override
		public List<Reimbursment> getPendingReimbursemnts() {
			try(Connection conn = ConnectionUtil.getConnection()){
				List<Reimbursment> rebur= new ArrayList<>();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursement WHERE approved = 0  ORDER BY rebur_id");
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					rebur.add(new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved")));
				}
				return rebur;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			return null;
		}

		@Override
		public List<Reimbursment> getApprovedReimbursemnts() {
			try(Connection conn = ConnectionUtil.getConnection()){
				List<Reimbursment> rebur= new ArrayList<>();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursement WHERE approved = 1  ORDER BY rebur_id");
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					rebur.add(new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved")));
				}
				return rebur;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			return null;
		}

		@Override
		public List<Reimbursment> getReimbursmentForEmployee(String emp) {
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				List<Reimbursment> rebur= new ArrayList<>();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM REIMBURSEMENT WHERE SUBMITTER_ID IN (SELECT EMP_ID FROM EMPLOYEE WHERE EMPLOYEE.EMP_USERNAME = ?)");
				stmt.setString(++index, emp);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					rebur.add(new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved")));
				}
				return rebur;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			return null;
		}
}
