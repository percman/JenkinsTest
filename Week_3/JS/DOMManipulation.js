window.onload = function(){
    document.getElementById("submitBtn").addEventListener("click", addFormDataToTable);
}

function addFormDataToTable(){
    //Gets text content from the form
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let occupation = document.getElementById("occupation").value;

    if(firstname && lastname && occupation){
        //We create the elements which are to be dynamically added
        let row = document.createElement("tr");
        let tdFirstname = document.createElement("td");
        let tdLastname = document.createElement("td");
        let tdOccupation = document.createElement("td");

        //We assign each td their corresponding value
        tdFirstname.textContent = firstname;
        tdLastname.textContent = lastname;
        tdOccupation.textContent = occupation;

        //Add the table data to the table row
        row.appendChild(tdFirstname);
        row.appendChild(tdLastname);
        row.appendChild(tdOccupation);
    
        //Final Step: add the row to the table body
        document.getElementById("table-body").appendChild(row);

        document.getElementById("firstname").value = "";
        document.getElementById("lastname").value = "";
        document.getElementById("occupation").value = "";


    }   
    
}
