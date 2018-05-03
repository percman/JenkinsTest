window.onload = function() {
    document.getElementById("pokemonSubmit")
            .addEventListener("click", getPokemon);
}

function getPokemon() {
    // Get a reference to the Pokemon ID that the user submits
    let pokemonId = document.getElementById("pokemonId").value;

    // Step 1: Create the XMLHttpRequest Object
    let xhr = new XMLHttpRequest();

    // Step 2: Define the onreadystatechange callback function
    xhr.onreadystatechange = function() {
        // Step 5: Handle the response
        if (xhr.readyState === 4 && xhr.status === 200) {
            let pokemon = JSON.parse(xhr.responseText);
            setValues(pokemon);
        }
    };

    // Step 3: Call the open() method
    xhr.open("GET", "https://pokeapi.co/api/v2/pokemon/" + pokemonId);

    // Step 4: Call the send() method
    xhr.send();
}

function setValues(pokemon) {
    // Set the pokemon name to the pokemon name span, in the well
    document.getElementById("pokemonName").textContent = pokemon.name.charAt(0).toUpperCase() + pokemon.name.slice(1);
    let myTypes = [];
    for (let t of pokemon.types) {
        myTypes.push(t.type.name);
    }
    let primary = myTypes[0].charAt(0).toUpperCase() + myTypes[0].slice(1);
    let secondary = (myTypes[1] == null) ? "N/A" : myTypes[1].charAt(0).toUpperCase() + myTypes[1].slice(1);
    document.getElementById("primary").textContent = primary;
    document.getElementById("secondary").textContent = secondary;
    let pokemonImg = document.getElementById("pokemonImage");
    pokemonImg.setAttribute("src", pokemon.sprites.front_default);
    pokemonImg.setAttribute("alt", pokemon.name.charAt(0).toUpperCase() + pokemon.name.slice(1));
}