window.onload = function () {
    getEmployees();
}

function getEmployees() {
    let xhr = new XMLHttpRequest();

    xhr.open("GET", "http://localhost:8080/EmployeeReimbursementSystem/getEmployees.ajax");

    xhr.send();

    xhr.onreadystatechange = function () {

        if (xhr.readyState === 4 && xhr.status === 200) {

            let employees = JSON.parse(xhr.responseText);

            for (let employee of employees) {
                let id = employee.id;
                let firstname = employee.firstname;
                let lastname = employee.lastname;
                
                let row = document.createElement("tr");
                let tdId = document.createElement("td");
                let tdFirstname = document.createElement("td");
                let tdLastname = document.createElement("td");

                tdId.textContent = id;
                tdFirstname.textContent = firstname;
                tdLastname.textContent = lastname;

                row.appendChild(tdId);
                row.appendChild(tdFirstname);
                row.appendChild(tdLastname);

                document.getElementById("employeeTable").appendChild(row);


            }

        }
    }
}
