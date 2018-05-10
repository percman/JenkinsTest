package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.PokemonService;

public class AjaxDispatcher {

	private AjaxDispatcher() {}
	
	public static Object process(HttpServletRequest request, HttpServletResponse response) {
		switch(request.getRequestURI()) {
		case "/SecondDayServlets/getAllPokemon.ajax":
			return PokemonService.getAllPokemon();
		default:
			return new String("Not Implemented");
		}
	}
}
