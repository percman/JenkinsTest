package com.revature.factory;

public class PokemonNotFoundException extends Exception{

	private static final long serialVersionUID = 925607534058303810L;

	public PokemonNotFoundException() {
		super();
	}
	
	public PokemonNotFoundException(String msg) {
		super(msg);
	}
	
	public PokemonNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	
}
