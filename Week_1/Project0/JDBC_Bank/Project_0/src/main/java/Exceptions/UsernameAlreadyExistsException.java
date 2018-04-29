package Exceptions;

public class UsernameAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -3262229373073115964L;
	public UsernameAlreadyExistsException() {
		System.out.println("\n\n\t\t***THAT USERNAME ALREADY EXISTS***");
	}

}
