window.onload = function() {
    populateTodosTable();
}

function populateTodosTable() {
    // STEP 1: create an XMLHttpRequest object
    let xhr = new XMLHttpRequest();

    // STEP 2: add a callback function to onreadystatechange
    xhr.onreadystatechange = function() {
        //STEP 5: handle the sponse
        if (xhr.readyState === 4 && xhr.status === 200) {
            let responseObj = JSON.parse(xhr.responseText);
            for (let element of responseObj) {
                // grab the properties from the element in the JSON response
                let id = element.id;
                let userId = element.userId;
                let title = element.title.charAt(0).toUpperCase() + element.title.slice(1);
                let completed = element.completed ? "Yes" : "No";

                // Dynamically create the HTML tags
                let row = document.createElement("tr");
                let tdId = document.createElement("td");
                let tdUserId = document.createElement("td");
                let tdTitle = document.createElement("td");
                let tdCompleted = document.createElement("td");

                tdId.textContent = id;
                tdUserId.textContent = userId;
                tdTitle.textContent = title;
                tdCompleted.textContent = completed;

                //Programatically Bootstrapify each tdCompleted
                (tdCompleted.textContent === "Yes") 
                ? tdCompleted.setAttribute("class", "success") 
                : tdCompleted.setAttribute("class", "danger");

                row.appendChild(tdId);
                row.appendChild(tdUserId);
                row.appendChild(tdTitle);
                row.appendChild(tdCompleted);
                
                document.getElementById("todos").appendChild(row);
            }
        }
    }
    // STEP 3: Call the open() method
    xhr.open("GET", "https://jsonplaceholder.typicode.com/todos");

    //STEP 4: Call the send() method
    xhr.send();
}
