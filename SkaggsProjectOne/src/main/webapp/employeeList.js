window.onload = function(){
  getEmployees();
}

function getEmployees() {

	let xhr = new XMLHttpRequest();
	console.log(xhr.readyState);
	xhr.onreadystatechange = function() {

		if (xhr.readyState == 4 && xhr.status == 200) {
			// Typical action to be performed when the document is ready:
		    let employees = JSON.parse(xhr.responseText);
		    for ( let employee of employees) {
				let name = employee.firstName + " " + employee.lastName;
				let userName = employee.userName;
				
				let row = document.createElement("tr");
				let tdName = document.createElement("td");
				let tdUserName = document.createElement("td");
				
				tdName.textContent = name;
				tdUserName.textContent = userName;
					
				row.appendChild(tdName);
				row.appendChild(tdUserName);
				
				document.getElementById("employeeTable").appendChild(row);
		    };
		}
	}
    xhr.open("POST", "http://localhost:8080/SkaggsProjectOne/getAllEmployees.ajax");
    xhr.send();
};

