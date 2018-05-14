package com.revature.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public String getMessage() {
        return "User not found!";
    }
}
