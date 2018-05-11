window.onload = function(){
  document.getElementById("getRequests").addEventListener("click", getRequests);
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
    	
      let requests = JSON.parse(xhr.responseText);
      
      let status = document.getElementById("requestSelector").value;
      console.log(status);
      for (let request of requests){
    	  
    	  if (document.getElementById("hidden_id").value == request.requestor_id && (status == 4 || request.status == status )){
        	  // Create all the necessary categories
        	  let category = request.category;
        	  if (category == 1){category = "Food";};
        	  if (category == 2){category = "Lodging";};
        	  if (category == 3){category = "Other";};
        	  if (category == 4){category = "Travel";};
              let status = request.status;
              if (status == 0){status = "Pending";};
              if (status == 1){status = "Approved";};
              let amount = request.amount;
              let message = request.message;
              let date = request.requestDate;
              
           // Dynamically create the HTML tags
              let row = document.createElement("tr");
              let tdCategory = document.createElement("td");
              let tdStatus = document.createElement("td");
              let tdAmount = document.createElement("td");
              let tdMessage = document.createElement("td");
              let tdDate = document.createElement("td");
              
           // We assign each td their corresponding value
              tdCategory.textContent = category;
              tdStatus.textContent = status;
              tdAmount.textContent = amount + " TiCos";
              tdMessage.textContent = message;
              tdDate.textContent = date;
              
           // We add each td to the tr
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