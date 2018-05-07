package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.reimbursement.Reimbursement;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	@Override
	public Employee getEmployee(int id) throws ClassNotFoundException {
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = c.prepareStatement("SELECT * FROM employeeTable, infoTable WHERE employeeId = " + id);
			ResultSet rs = stmt.executeQuery();
			Employee e = null;
			while(rs.next()) {
				e = new Employee(rs.getString("firstName"), rs.getString("middleInit"), 
						rs.getString("lastName"),rs.getString("userName"), rs.getString("userPassword"),
						rs.getInt("FINANCEMANAGER")==1);
			}
			return e;
			
		} catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public ArrayList<Employee> getAllEmployees() throws ClassNotFoundException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			ArrayList<Employee> employeeList = new ArrayList<>();
			System.out.println(employeeList.size());
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employeeTable, infoTable  WHERE EMPLOYEETABLE.employeeId = INFOTABLE.employeeId");			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {	
				employeeList.add(new Employee(rs.getString("firstName"), rs.getString("middleName"), 
						rs.getString("lastName"), rs.getString("userName"), rs.getString("userPassword"),
						rs.getInt("financemanager")==1));
			}
			return employeeList;
		} catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertEmployee(Employee e) throws ClassNotFoundException {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("{CALL insert_employee(?, ?, ?, ?, ?, ?)  }");
			stmt.setString(++index, e.getUserName());
			stmt.setString(++index, e.getPassword());
			stmt.setString(++index, e.getFirstName());
			stmt.setString(++index, e.getMiddleInit());
			stmt.setString(++index, e.getLastName());
			int fm = e.isFinanceManager ? 1 : 0;
			stmt.setInt(++index, fm);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return false;
	}


	@Override
	public boolean deleteEmployee(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertRequest(Reimbursement r) throws ClassNotFoundException {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("{CALL make_request(?, ?, ?, ?, ?)  }");
			stmt.setInt(++index, r.getReimbursementId());
			stmt.setInt(++index, r.getRequesterId());
			stmt.setInt(++index, r.getApproverId());
			stmt.setString(++index, r.getCategory());
			stmt.setString(++index, r.getStatus());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return false;
	}
}
