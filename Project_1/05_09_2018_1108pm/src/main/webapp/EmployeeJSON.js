window.onload = function(){
  document.getElementById("getEmployees").addEventListener("click", getRequests);
  getRequests();
}

function getRequests(){

  // Step 1: create the XMLHttpRequest Object
  let xhr = new XMLHttpRequest();

  // Step 2: Define the onreadystatechange callback function
  xhr.onreadystatechange = function(){
    // Step 5: handle the response
	  console.log(xhr.responseText);
      console.log(xhr.status);
    if(xhr.readyState === 4 && xhr.status === 200){
    	
    	// Delete the old table body
    	let new_tbody = document.createElement("tbody");
    	document.getElementById("requestsTable").innerHTML = "";
    	
      let employees = JSON.parse(xhr.responseText);
      
      console.log(status);
      for (let employee of employees){
    	  
        	  // Create all the necessary categories
        	  let id = employee.id;
              let lastname = employee.lastname;
              let firstname = employee.firstname;
              let username = employee.username;
              
           // Dynamically create the HTML tags
              let row = document.createElement("tr");
              let tdId = document.createElement("td");
              let tdLastname = document.createElement("td");
              let tdFirstname = document.createElement("td");
              let tdUsername = document.createElement("td");
              
           // We assign each td their corresponding value
              tdId.textContent = id;
              tdLastname.textContent = lastname;
              tdFirstname.textContent = firstname;
              tdUsername.textContent = username;
              
           // We add each td to the tr
              row.appendChild(tdId);
              row.appendChild(tdLastname);
              row.appendChild(tdFirstname);
              row.appendChild(tdUsername);
              
           // Append the row to the tdBody
              document.getElementById("requestsTable").appendChild(row);
      }
    }
  };

  // Step 3: Call the open() method
  xhr.open("GET", "http://localhost:8080/Project_1/getAllEmployees.ajax");

  // Step 4: Call the send() method
  xhr.send();
}