window.onload = function(){
  getRequests();
}

//window.onClick = function() {
//	displayImage();
//}
function getRequests() {

	let xhr = new XMLHttpRequest();
	console.log(xhr.readyState);
	xhr.onreadystatechange = function() {

		if (xhr.readyState == 4 && xhr.status == 200) {
			// Typical action to be performed when the document is ready:
		    let employees = JSON.parse(xhr.responseText);
		    for ( let employee of employees) {
		    	let eid = employee.reimbursementId;
				let category = employee.category;
				let status_n = employee.status;
				if (status_n == 0) {
					status = 'Pending';
				}
				if (status_n > 0) {
					status = 'Approved';
				}
				if (status_n < 0) {
					status = 'Rejected';
				}
				let amount = employee.amount;
				let ds = employee.dateSubmitted;
				let name = employee.requesterFirstName + " " + employee.requesterLastName;
				let a_name = employee.approverFirstName + " " + employee.approverLastName;
				if(employee.approverFirstName == null) {
					a_name = 'N/A';
				}
				
				let row = document.createElement("tr");
				let tdId = document.createElement("td");
				let tdCat = document.createElement("td");
				let tdStat = document.createElement("td");
				let tdAmount = document.createElement("td");
				let tdDs =  document.createElement("td");
				let tdName =  document.createElement("td");
				let tdAName = document.createElement("td");
				
				tdId.textContent = eid;
				tdCat.textContent = category;
				tdStat.textContent = status;
				tdAmount.textContent = amount;
				tdDs.textContent = ds;
				tdName.textContent = name;
				tdAName.textContent = a_name;
					
				row.appendChild(tdId);
				row.appendChild(tdCat);
				row.appendChild(tdStat);
				row.appendChild(tdAmount);
				row.appendChild(tdDs);
				row.appendChild(tdName);
				row.appendChild(tdAName);
				
				document.getElementById("requestTable").appendChild(row);
		    };
		}
	}
    xhr.open("POST", "http://localhost:8080/SkaggsProjectOne/getAllRequests.ajax");
    xhr.send();
};
function displayImage() {

	let xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let image = JSON.parse(xhr.responseText);
			console.log("imgage" + image);
			document.getElementById("receipt").src = "data:image/png;base64," + image;
			console.log("Here we are");
		}
	}
	
    xhr.open("POST", "http://localhost:8080/SkaggsProjectOne/getImage.ajax");
    xhr.send();
};

