
public class UserInfo {

	String username = null;
	String password = null;
	
	UserInfo(String newUsername, String newPassword)
	{
		super();
		username = newUsername;
		password = newPassword;
		boolean approved = false;
		boolean admin = false;
	}

	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", password=" + password + "]";
	}
	
	

}
