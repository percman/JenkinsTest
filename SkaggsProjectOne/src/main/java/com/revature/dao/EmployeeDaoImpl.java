package com.revature.dao;

import java.awt.Image;
import java.sql.Blob;
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
			PreparedStatement stmt = c.prepareStatement("SELECT * FROM EMPLOYEETABLE e FULL OUTER "
					+ "JOIN INFOTABLE i ON e.employeeId = i.employeeId WHERE e.employeeid = " + id);
			ResultSet rs = stmt.executeQuery();
			Employee e = null;
			while(rs.next()) {
				e = new Employee(rs.getInt("employeeId"), rs.getString("firstName"), rs.getString("middleName"), 
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
	public Employee getEmployee(String username) throws ClassNotFoundException {
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = c.prepareStatement("SELECT * FROM EMPLOYEETABLE e FULL OUTER "
					+ "JOIN INFOTABLE i ON e.employeeId = i.employeeId WHERE userName = '" + username + "'");
			ResultSet rs = stmt.executeQuery();
			Employee e = null;
			while(rs.next()) {
				e = new Employee(rs.getInt("employeeId"), rs.getString("firstName"), rs.getString("middleName"), 
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
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employeeTable, infoTable  "
					+ "WHERE EMPLOYEETABLE.employeeId = INFOTABLE.employeeId ORDER BY EMPLOYEETABLE.employeeId");			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {	
				employeeList.add(new Employee(rs.getInt("employeeId"),rs.getString("firstName"), rs.getString("middleName"), 
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
	public ArrayList<Reimbursement> getAllRequests() throws ClassNotFoundException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			ArrayList<Reimbursement> rList = new ArrayList<>();
			System.out.println(rList.size());
			PreparedStatement stmt = conn.prepareStatement("SELECT * " + 
					"FROM reimbursementTable R, infoTable I " + 
					"WHERE R.requesterid = I.employeeid ORDER BY reid");			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {	
				int reid = rs.getInt("reid");
				int requesterId = rs.getInt("requesterId");
				int approverId = rs.getInt("approverId");
				String category = rs.getString("categoryName");
				int status = rs.getInt("status");
				int amount = rs.getInt("amount");
				String dateSubmitted = rs.getString("dateSubmitted");
				String dateCompleted = " ";
				if (rs.getString("dateCompleted") !=null) {
					dateCompleted = rs.getString("dateCompleted");
				}
				String requesterFirstName = rs.getString("firstName");
				String requesterLastName = rs.getString("lastName");
				rList.add(new Reimbursement(reid, requesterId, approverId, category, status, amount, dateSubmitted, dateCompleted, 
				requesterFirstName, requesterLastName, null, null, null));
			}
			
			return rList;
		} catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}
	@Override
	public byte[] getImage(int reid) {
		try (Connection conn = ConnectionUtil.getConnection()) {
	    	byte[] image = null;
			PreparedStatement stmt = conn.prepareStatement("SELECT img FROM reimbursementTable " + 
					"WHERE reid = " +  reid);			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {	
				Blob blob = rs.getBlob("img");
				int blobLength = (int) blob.length();  
				image = blob.getBytes(1, blobLength);
				blob.free();
				System.out.println("Image" + image);
			}
			return image;
		} catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;		
	}
	//Gets all the requests from employee e
	@Override
	public ArrayList<Reimbursement> getMyRequests(Employee e) throws ClassNotFoundException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			ArrayList<Reimbursement> rList = new ArrayList<>();
			System.out.println(e);
			PreparedStatement stmt = conn.prepareStatement("SELECT * " + 
					"FROM reimbursementTable R, infoTable I " + 
					"WHERE R.requesterid = I.employeeid and I.employeeid = " + e.getEmployeeId() + " ORDER BY reid");			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {	
				int reid = rs.getInt("reid");
				int requesterId = rs.getInt("requesterId");
				int approverId = rs.getInt("approverId");
				String category = rs.getString("categoryName");
				int status = rs.getInt("status");
				int amount = rs.getInt("amount");
				String dateSubmitted = rs.getString("dateSubmitted");
				String dateCompleted = " ";
				if (rs.getString("dateCompleted") !=null) {
					dateCompleted = rs.getString("dateCompleted");
				}
				String requesterFirstName = rs.getString("firstName");
				String requesterLastName = rs.getString("lastName");
				rList.add(new Reimbursement(reid, requesterId, approverId, category, status, amount, dateSubmitted, dateCompleted, 
				requesterFirstName, requesterLastName,null,null, null));
			}
			
			return rList;
		} catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}
	@Override
	public Reimbursement getRequest(int id) throws ClassNotFoundException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			Reimbursement r = null;
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursementTable WHERE reid= " + id);			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {	
				int reid = rs.getInt("reid");
				int requesterId = rs.getInt("requesterId");
				int approverId = rs.getInt("approverId");
				String category = rs.getString("categoryName");
				int status = rs.getInt("status");
				int amount = rs.getInt("amount");
				String dateSubmitted = rs.getString("dateSubmitted");
				String dateCompleted = " ";
				if (rs.getString("dateCompleted") !=null) {
					dateCompleted = rs.getString("dateCompleted");
				}
				r = new Reimbursement(reid, requesterId, approverId, category, status, amount, dateSubmitted, dateCompleted, 
				" ", " ",null,null, null);
			}
			
			return r;
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
			stmt.setInt(++index, r.getRequesterId());
			stmt.setString(++index, r.getCategory());
			stmt.setInt(++index, r.getStatus());
			stmt.setInt(++index, r.getAmount());
			stmt.setBlob(++index, r.getImg());
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
	public boolean updateEmployee(Employee e) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String firstName = e.getFirstName();
			String lastName = e.getLastName();
			String userName = e.getUserName();
			String password = e.getPassword();
			PreparedStatement stmt = conn.prepareStatement("{CALL update_employee(?, ?, ?, ?)  }");
			stmt.setString(++index, firstName);
			stmt.setString(++index, lastName);
			stmt.setString(++index, userName);
			stmt.setString(++index, password);
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
	public boolean updateEmployeeWithoutPassword(Employee e) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String firstName = e.getFirstName();
			String lastName = e.getLastName();
			String userName = e.getUserName();
			PreparedStatement stmt = conn.prepareStatement("{CALL update_employee_no_password(?, ?, ?)  }");
			stmt.setString(++index, firstName);
			stmt.setString(++index, lastName);
			stmt.setString(++index, userName);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return false;
	}
	@Override // Get the hashed password from the database
    public String getPasswordHash(Employee employee) {
        int index = 0;
        try (Connection conn = ConnectionUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?,?) AS HASH FROM dual");
            stmt.setString(++index, employee.getUserName());
            stmt.setString(++index, employee.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	System.out.println(rs.getString("HASH"));
                return rs.getString("HASH");
            }
        } catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
        }
        return null;
    }
	@Override
	public boolean updateRequest(Employee e, int reid, String approved) {
		int index = 0;
		int status = 0;
		if (approved.equals("Approve")) {
			status = 1;
		}
		if (approved.equals("Deny")) {
			status = -1;
		}
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE reimbursementTable "
					+ " SET status= (?), approverid=(?), "
					+ "datecompleted=(select current_date FROM dual) WHERE reid=(?)");
			stmt.setInt(++index, status);
			stmt.setInt(++index, e.getEmployeeId());
			stmt.setInt(++index, reid);
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
