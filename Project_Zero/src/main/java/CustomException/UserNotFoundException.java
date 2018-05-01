package CustomException;

public class UserNotFoundException extends Exception{

	
/**
	 * 
	 */
	private static final long serialVersionUID = -6509136019166384994L;

	public UserNotFoundException() {}
	
	public UserNotFoundException(String s) {
		super(s);
		
	}

}
