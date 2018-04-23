import java.io.Serializable;

public class UserInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	String username = null;
	String password = null;
	boolean approved = false;
	boolean locked = false;
	GroceryList myGroceryList = new GroceryList();
	
	UserInfo(){}
	
	UserInfo(String newUsername, String newPassword)
	{
		super();
		username = newUsername;
		password = newPassword;
		approved = false;
		locked = false;
		myGroceryList = new GroceryList();
	}

	
	public GroceryList getMyGroceryList() {
		return myGroceryList;
	}

	public void setMyGroceryList(GroceryList myGroceryList) {
		this.myGroceryList = myGroceryList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	
	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		UserInfo other = (UserInfo) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Username: " + username + "\nPassword: " + password 
				+ "\nApproved: " + approved + "\nlocked:"
				+ locked + "\n\n";
	}



	
	

}
