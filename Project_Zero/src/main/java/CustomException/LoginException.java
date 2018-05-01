package CustomException;

public class LoginException extends Exception{


	private static final long serialVersionUID = 800271479301531421L;
	
	
	public LoginException() {}
	
	public LoginException(String s) {
		super(s);
		
	}

}
