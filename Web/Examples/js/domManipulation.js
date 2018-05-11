window.onload = () => {
    document.getElementById("submitBtn")
            .addEventListener("click", addFormDataToTable);
    document.getElementById("resetBtn")
            .addEventListener("click", clearTable);
}

function addFormDataToTable() {
    //Gets text content from form.
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let occupation = document.getElementById("occupation").value;
    
    //creates elements to be added.
    let row = document.createElement("tr");
    let tdFirstname = document.createElement("td");
    let tdLastname = document.createElement("td");
    let tdOccupation = document.createElement("td");

    tdFirstname.textContent = firstname;
    tdLastname.textContent = lastname;
    tdOccupation.textContent = occupation;
    row.appendChild(tdFirstname);
    row.appendChild(tdLastname);
    row.appendChild(tdOccupation);
    
    document.getElementById("table-body").appendChild(row);
        
    document.getElementById("firstname").value = "";
    document.getElementById("lastname").value = "";
    document.getElementById("occupation").value = "";
}

function clearTable() {
    let node = document.getElementById("table-body").getElementsByTagName("tr");
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }

}
