package com.revature.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.revature.model.*;


public class BankDatabase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6048399431657189215L;
	private HashMap<String, User> user;
	private HashMap<String, Customer> customer;
	private HashMap<String, Admin> admin;
	private HashMap<String, String> loginInfo;
	private List<Customer> approvalList;
	

	public BankDatabase() {
		this.user = new HashMap<>();
		this.customer = new HashMap<>();
		this.admin = new HashMap<>();
		this.loginInfo = new HashMap<>();
		this.approvalList = new ArrayList<>();
	}

	

	

	public HashMap<String, User> getUser() {
		return user;
	}

	public void setUser(HashMap<String, User> user) {
		this.user = user;
	}
	
	public HashMap<String, Customer> getCustomer(){
		return customer;
	}
	
	public void setCustomer(HashMap<String, Customer> customer) {
		this.customer = customer;
	}
	
	public HashMap<String, Admin> getAdmin(){
		return admin;
	}
	
	public void setAdmin(HashMap<String, Admin> admin) {
		this.admin = admin;
	}
	
	
	public HashMap<String, String> getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(HashMap<String, String> loginInfo) {
		this.loginInfo = loginInfo;
	}
	
	public int sizeOfUser() {
			return user.size();
	}
	
	public int sizeOfCustomer() {
		return customer.size();
	}
	
	public List<Customer> getApprovalList(){
		return approvalList;
	}
	
	public void setApprovalList(List<Customer> approvalList) {
		this.approvalList = approvalList;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((approvalList == null) ? 0 : approvalList.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((loginInfo == null) ? 0 : loginInfo.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankDatabase other = (BankDatabase) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (approvalList == null) {
			if (other.approvalList != null)
				return false;
		} else if (!approvalList.equals(other.approvalList))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (loginInfo == null) {
			if (other.loginInfo != null)
				return false;
		} else if (!loginInfo.equals(other.loginInfo))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	

}
