window.onload = function(){
  getRequests();
}

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
				
				let row = document.createElement("tr");
				let tdId = document.createElement("td");
				let tdCat = document.createElement("td");
				let tdStat = document.createElement("td");
				let tdAmount = document.createElement("td");
				let tdDs =  document.createElement("td");
				
				tdId.textContent = eid;
				tdCat.textContent = category;
				tdStat.textContent = status;
				tdAmount.textContent = amount;
				tdDs.textContent = ds;
				
				row.appendChild(tdId);
				row.appendChild(tdCat);
				row.appendChild(tdStat);
				row.appendChild(tdAmount);
				row.appendChild(tdDs);
				
				document.getElementById("requestTable").appendChild(row);
		    };
		}
	}
    xhr.open("POST", "http://localhost:8080/SkaggsProjectOne/getRequests.ajax");
    xhr.send();
};

