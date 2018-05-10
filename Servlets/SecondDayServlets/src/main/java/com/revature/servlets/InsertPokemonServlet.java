package com.revature.servlets;

import java.io.IOException;
import java.util.Enumeration;

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
		response.setContentType("application/x-www-form-urlencoded");
		
		// Get a reference to an ObjectMapper to read the JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = request.getReader().readLine();
		
		// Added this print statement
		System.out.println(json);
		
		// Read the JSON value 
		Pokemon temp = mapper.readValue(json, Pokemon.class);
		
		// Added this print statement
		System.out.println(temp);
		
		// Call our PokemonService to insert the Pokemon
		PokemonService.insertPokemon(temp);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}







