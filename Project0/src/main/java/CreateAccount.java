public class CreateAccount {
	String username = null;
	String password = null;
	
	CreateAccount(String newUsername, String newPassword)
	{
		super();
		username = newUsername;
		password = newPassword;
	}

	
	@Override
	public String toString() {
		return "CreateAccount username=" + username + ", password=" + password + "";
	}

}
	
