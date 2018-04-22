import java.io.Serializable;

public class UserInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	String username = null;
	String password = null;
	boolean approved = false;
	boolean admin = false;
	
	UserInfo(){}
	
	UserInfo(String newUsername, String newPassword)
	{
		super();
		username = newUsername;
		password = newPassword;
		approved = false;
		admin = false;
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
		return "-UserInfo-\nUsername: " + username + " Password:" + password;
	}
	
	

}
