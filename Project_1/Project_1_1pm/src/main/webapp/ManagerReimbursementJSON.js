window.onload = function (){
  document.getElementById("getRequests").addEventListener("click", getRequests);
  document.getElementById("getEmployees").addEventListener("click", getEmployees);
  getRequests();
  getEmployees();
}

function getRequests(){

  // Step 1: create the XMLHttpRequest Object
  let xhr = new XMLHttpRequest();

  // Step 2: Define the onreadystatechange callback function
  xhr.onreadystatechange = function(){
    // Step 5: handle the response
    if(xhr.readyState === 4 && xhr.status === 200){
    	
    	// Delete the old table body
    	document.getElementById("requestsTable").innerHTML = "";
    	
      let requests = JSON.parse(xhr.responseText);
      
      let status = document.getElementById("requestSelector").value;
      console.log(status);
      for (let request of requests){
    	  
    	  if(status == request.status || status == 4){
    		  
        	  // Create all the necessary categories
      	  	let id = request.requestor_id;
          	  let category = request.category;
          	  if (category == 1){category = "Food";};
          	  if (category == 2){category = "Lodging";};
          	  if (category == 3){category = "Other";};
          	  if (category == 4){category = "Travel";};
                let status = request.status;
                if (status == 0){status = "Pending";};
                if (status == 1){status = "Approved";};
                if (status == -1){status = "Declined";};
                let amount = request.amount;
                let message = request.message;
                let date = request.requestDate;
                
             // Dynamically create the HTML tags
                let row = document.createElement("tr");
                let tdId = document.createElement("td");
                let tdCategory = document.createElement("td");
                let tdStatus = document.createElement("td");
                let tdAmount = document.createElement("td");
                let tdMessage = document.createElement("td");
                let tdDate = document.createElement("td");
                
                // Stuff
                if (status == "Pending"){
              	  tdStatus.className = "btn btn-warning viewRequestButton"
                }
                // Stuff
                if (status == "Approved"){
              	  tdStatus.className = "btn btn-success viewRequestButton"
                }              // Stuff
                if (status == "Declined"){
              	  tdStatus.className = "btn btn-danger viewRequestButton"
                }
                tdStatus.id = request.reimbursement_id;
                tdStatus.style.width = "70px";
                tdStatus.style.height = "65px";
                tdStatus.setAttribute('onclick','div_show(this.id,id)');
                
             // We assign each td their corresponding value
                tdId.textContent = id;
                tdCategory.textContent = category;
                tdStatus.textContent = status;
                tdAmount.textContent = amount + " TiCos";
                tdMessage.textContent = "...";
                tdDate.textContent = date;
                
             // We add each td to the tr
                row.appendChild(tdId);
                row.appendChild(tdCategory);
                row.appendChild(tdStatus);
                row.appendChild(tdAmount);
                row.appendChild(tdMessage);
                row.appendChild(tdDate);
                
             // Append the row to the tdBody
                document.getElementById("requestsTable").appendChild(row);
    	  }
      }
    }
  };

  // Step 3: Call the open() method
  xhr.open("GET", "http://localhost:8080/Project_1/getAllRequests.ajax");

  // Step 4: Call the send() method
  xhr.send();
}

function getEmployeeRequests(){

	  // Step 1: create the XMLHttpRequest Object
	  let xhr = new XMLHttpRequest();
	  let thisId = this.id;
	  
	  // Step 2: Define the onreadystatechange callback function
	  xhr.onreadystatechange = function(){
	    // Step 5: handle the response
	    if(xhr.readyState === 4 && xhr.status === 200){
	    	
	    	// Delete the old table body
	    	document.getElementById("requestsTable").innerHTML = "";
	    	
	      let requests = JSON.parse(xhr.responseText);
	      
	      let status = document.getElementById("requestSelector").value;
	      
	      for (let request of requests){
	    	  console.log(thisId);
	    	  if(request.requestor_id == thisId && (status == request.status || status == 4)){
	    		  
	        	  // Create all the necessary categories
	      	  	let id = request.requestor_id;
	          	  let category = request.category;
	          	  if (category == 1){category = "Food";};
	          	  if (category == 2){category = "Lodging";};
	          	  if (category == 3){category = "Other";};
	          	  if (category == 4){category = "Travel";};
	                let status = request.status;
	                if (status == 0){status = "Pending";};
	                if (status == 1){status = "Approved";};
	                if (status == -1){status = "Declined";};
	                let amount = request.amount;
	                let message = request.message;
	                let date = request.requestDate;
	                
	             // Dynamically create the HTML tags
	                let row = document.createElement("tr");
	                let tdId = document.createElement("td");
	                let tdCategory = document.createElement("td");
	                let tdStatus = document.createElement("td");
	                let tdAmount = document.createElement("td");
	                let tdMessage = document.createElement("td");
	                let tdDate = document.createElement("td");
	                
	                // styling Stuff
	                if (status == "Pending"){
	              	  tdStatus.className = "btn btn-warning viewRequestButton"
	                }
	                // Styling Stuff
	                if (status == "Approved"){
	              	  tdStatus.className = "btn btn-success viewRequestButton"
	                }              // Stuff
	                if (status == "Declined"){
	              	  tdStatus.className = "btn btn-danger viewRequestButton"
	                }
	                tdStatus.id = request.reimbursement_id;
	                tdStatus.style.width = "70px";
	                tdStatus.style.height = "65px";
	                tdStatus.setAttribute('onclick','div_show(this.id,id)');
	                
	             // We assign each td their corresponding value
	                tdId.textContent = id;
	                tdCategory.textContent = category;
	                tdStatus.textContent = status;
	                tdAmount.textContent = amount + " TiCos";
	                tdMessage.textContent = "...";
	                tdDate.textContent = date;
	                
	             // We add each td to the tr
	                row.appendChild(tdId);
	                row.appendChild(tdCategory);
	                row.appendChild(tdStatus);
	                row.appendChild(tdAmount);
	                row.appendChild(tdMessage);
	                row.appendChild(tdDate);
	                
	             // Append the row to the tdBody
	                document.getElementById("requestsTable").appendChild(row);
	    	  }
	      }
	    }
	  };
	  // Step 3: Call the open() method
	  xhr.open("GET", "http://localhost:8080/Project_1/getAllRequests.ajax");

	  // Step 4: Call the send() method
	  xhr.send();
}

function getEmployees(){

	  // Step 1: create the XMLHttpRequest Object
	  let xhr = new XMLHttpRequest();

	  // Step 2: Define the onreadystatechange callback function
	  xhr.onreadystatechange = function(){
	    // Step 5: handle the response
	    if(xhr.readyState === 4 && xhr.status === 200){
	    	
	    	// Delete the old table body
	    	document.getElementById("employeesTable").innerHTML = "";
	    	
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
	              
	              	// Set up information for each button
	              	tdId.className = "btn btn-info rowButton"
	            	tdId.style.width = "117px";
	              	tdId.style.height = "65px";
	            	tdId.id = id;      
	            	
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
	          		document.getElementById("employeesTable").appendChild(row);
	      }
	      
      	// Get an event listener on each button
      	let elements = document.getElementsByClassName("rowButton");    	
      	for (let element of elements){
      		console.log(element);
      		
      		element.addEventListener("click", getEmployeeRequests);
      	}
	    }
	  };

	  // Step 3: Call the open() method
	  xhr.open("GET", "http://localhost:8080/Project_1/getAllEmployees.ajax");

	  // Step 4: Call the send() method
	  xhr.send();
}

//Validating Empty Field
function check_empty() {
if (document.getElementById('name').value == "" || document.getElementById('email').value == "" || document.getElementById('msg').value == "") {
alert("Fill All Fields !");
} else {
document.getElementById('form').submit();
alert("Form Submitted Successfully...");
}
}
//Function To Display Popup
function div_show(requestId,requestorId) {
	console.log("Show " + requestId + " " + requestorId);
document.getElementById('abc').style.display = "block";
}
//Function to Hide Popup
function div_hide(){
document.getElementById('abc').style.display = "none";
}

function approveUser(){
	console.log("approved");
	console.log(this.id);
}
function denyUser(){
	console.log("denied");
	console.log(this.id);
}