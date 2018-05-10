package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Pokemon;
import com.revature.service.PokemonService;

public class InsertPokemonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertPokemonServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get a reference to an Object Mapper to read the JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = request.getReader().readLine();
		
		// Read the JSON value
		Pokemon temp = mapper.readValue(json, Pokemon.class);
		
		// Call our Pokemon Service to insert the pokemon
		PokemonService.insertPokemon(temp);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
