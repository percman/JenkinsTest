window.onload = function () {
    document.getElementById("submitbtn").addEventListener("click", addFormDataToTable);
    document.getElementById("submitbtn").addEventListener("click", clearForm);
}


function addFormDataToTable() {
    // Gets text content from the form
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let ocupation = document.getElementById("ocupation").value;

    // We create the elements which are to be dynamically added
    let row = document.createElement("tr");
    let tdFirstname = document.createElement("td")
    let tdLastname = document.createElement("td");
    let tdOcupation = document.createElement("td");

    // We assign each td their corresponding value
    tdFirstname.textContent = firstname;
    tdLastname.textContent = lastname;
    tdOcupation.textContent = ocupation;

    // Add the table datas to the table row
    row.appendChild(tdFirstname);
    row.appendChild(tdLastname);
    row.appendChild(tdOcupation);

    // Final step: add th row to the table body
    document.getElementById("tablebody").appendChild(row); 
}

function clearForm() {
    document.getElementById("firstname").value = "";
    document.getElementById("lastname").value = "";
    document.getElementById("ocupation").value = "";

}