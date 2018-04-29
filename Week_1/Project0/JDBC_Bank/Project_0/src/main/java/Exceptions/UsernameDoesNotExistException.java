package Exceptions;

public class UsernameDoesNotExistException extends Exception {

	private static final long serialVersionUID = -7262615537651151197L;
	
	public UsernameDoesNotExistException() {
		System.out.println("\n\n\t\t***USER NAME DOES NOT EXIST***");
	}
}
