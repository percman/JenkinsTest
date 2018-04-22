package save_0;

public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 5113828884107708309L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }

    public UserNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
