window.onload = function() {
    populateTodosTable();
}

function populateTodosTable() {
    // Step 1: Create an XMLHttpRequest object
    let xhr = new XMLHttpRequest();

    // Step 2: Define a callback function to onreadystatechange
    xhr.onreadystatechange = function() {
        //Step 5: Handle the response
        if (xhr.readyState === 4 && xhr.status === 200) {
            let todoObject = JSON.parse(xhr.responseText);
            // for...of iterates similiar to Java's enhanced for loop
            for (let todo of todoObject) {
                // Get the properties of each element in the JSON Response
                let id = todo.id;
                let userId = todo.userId;
                let title = todo.title.charAt(0).toUpperCase() + todo.title.slice(1);
                let completed = (todo.completed === true) ? "Yes" : "No";

                // Dynamically create the HTML tags
                let row = document.createElement("tr");
                let tdId = document.createElement("td");
                let tdUserId = document.createElement("td");
                let tdTitle = document.createElement("td");
                let tdCompleted = document.createElement("td");

                // We assign each td their corresponding value
                tdId.textContent = id;
                tdUserId.textContent = userId;
                tdTitle.textContent = title;
                tdCompleted.textContent = completed;

                // Programatically Bootstrapify each tdCompleted
                (tdCompleted.textContent === "Yes") ? tdCompleted.setAttribute("class", "success") : tdCompleted.setAttribute("class", "danger");

                // We add each td to the tr
                row.appendChild(tdId);
                row.appendChild(tdUserId);
                row.appendChild(tdTitle);
                row.appendChild(tdCompleted);

                // Append the row to the tbody
                document.getElementById("todos").appendChild(row);
            }
        }
    }

    // Step 3: Call the open() method
    xhr.open("GET", "https://jsonplaceholder.typicode.com/todos");

    // Step 4: Call the send() method
    xhr.send();
}