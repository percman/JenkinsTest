package model;

import exception.CreatePersonException;

public class PersonFactory {
	public static Person create(String type, String name, String password, int balance, boolean lock) throws CreatePersonException{
		switch(type) {
		case "administrator":
			return new Administrator(name, password);
		case "user":
			return new User(name, password, balance, lock);
		default:
			throw new CreatePersonException("PersonFactory#create");
		}
	}
}
