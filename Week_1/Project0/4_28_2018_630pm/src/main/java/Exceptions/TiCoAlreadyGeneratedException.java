package Exceptions;

public class TiCoAlreadyGeneratedException extends Exception {

	private static final long serialVersionUID = 7325669773214856697L;
	
	public TiCoAlreadyGeneratedException() {
		System.out.println("\n\n\t\tA TICO HAS ALREADY BEEN GENERATED THIS HOUR\n");
	}

}
