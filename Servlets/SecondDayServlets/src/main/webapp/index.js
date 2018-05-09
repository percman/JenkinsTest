window.onload = function() {
	document.getElementById("pokemonSubmitBtn")
			.addEventListener("click", addPokemon);
}

function addPokemon() {
	let xhr = new XMLHttpRequest();
	// We do not need to assign a callback to the onreadystatechange because we are not 
	// Expecting a response
	
	// Getting the text value entered to the input tag
	let pokemonName = document.getElementById("pokemonName").value;
	let pokemonType = document.getElementById("pokemonType").value;
	
	// We use POST, since that is what we have defined in our InsertPokemonServlet
	xhr.open("POST", "http://localhost:8080/SecondDayServlets/InsertPokemonServlet");
	
	// Did you know xhr.send() could take an argument??
	xhr.send(JSON.stringify({name: pokemonName, type: pokemonType}));
	
	// Clear the text fields
	document.getElementById("pokemonName").value = "";
	document.getElementById("pokemonType").value = "";
}