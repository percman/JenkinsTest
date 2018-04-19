package com.revature.users;


import java.io.Serializable;
import java.util.HashMap;

public class ProjectUsers extends SerializationOfUsers implements Serializable{

	
	private static final long serialVersionUID = -4269544628204763811L;

	private String Name; 
	private boolean AdminStatus;
	private double Balance;
	
	public ProjectUsers() {}


	public ProjectUsers(String name, boolean adminstatus, double balance) {
		super();
		this.Name = name;
		AdminStatus = adminstatus;
		Balance = balance;
	}


	


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public boolean isAdminStatus() {
		return AdminStatus;
	}


	public void setAdminStatus(boolean adminStatus) {
		AdminStatus = adminStatus;
	}


	public double getBalance() {
		return Balance;
	}


	public void setBalance(double balance) {
		Balance = balance;
	}
	
	
	


	@Override
	public String toString() {
		return "[Name=" + Name + ", AdminStatus=" + AdminStatus + ", Balance=" + Balance + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (AdminStatus ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(Balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
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
		ProjectUsers other = (ProjectUsers) obj;
		if (AdminStatus != other.AdminStatus)
			return false;
		if (Double.doubleToLongBits(Balance) != Double.doubleToLongBits(other.Balance))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		return true;
	}


	public static void addProjectUserAsAdmin() {

		System.out.println("Please enter the username: ");
		String username = aquireLine();
		
		System.out.println("Is this person an admin? T or F");
		String adminstatusstring = aquireLine(); 
		boolean adminstatus;
		if(adminstatusstring.equals("T"))
			adminstatus = true;
		else 
			adminstatus = false;
		
		System.out.println("What is the beginning balance?");
		String balancestring = aquireLine();
		Double doubleObject = new Double(balancestring);
		double startingbalance = doubleObject.doubleValue();
		
		
		ProjectUsers newUser = new ProjectUsers (username, adminstatus, startingbalance);
		
		serializeUser(newUser, serializedUserFile);
		writeToAFileFromAFile(userFile, serializedUserFile);
		
		serializedUserFile.delete();
					
		System.out.println("The new user " + username + " has been created.\n");
	}
	
	// Easy way to have ProjectUsers object loaded into memory for testing 
	public static ProjectUsers addProjectUserAsAdmin(String username, boolean adminstatus, double startingbalance) {
		
		ProjectUsers newUser = new ProjectUsers (username, adminstatus, startingbalance);
		
		serializeUser(newUser, serializedUserFile);
		writeToAFileFromAFile(userFile, serializedUserFile);
		
		serializedUserFile.delete();
					
		System.out.println("The new user " + username + " has been created.\n");
		
		return newUser;
	}

	public static boolean adminCheck(HashMap<Integer, ProjectUsers> userHashData, String username) {
		int count = lineCount(userFile);
		
		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username))
				return userHashData.get(i).isAdminStatus();
		
		return false;
	}

}
