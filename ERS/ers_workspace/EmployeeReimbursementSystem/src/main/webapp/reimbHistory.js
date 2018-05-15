window.onload = function () {
    getAllReimbs();
}

function getAllReimbs() {
    let xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/getAllReimbs.ajax");

    xhr.send();

    xhr.onreadystatechange = function () {

        document.getElementById("allReimbsTable").innerHTML = "";

        if (xhr.readyState === 4 && xhr.status === 200) {

            let reimbs = JSON.parse(xhr.responseText);

            for (let reimb of reimbs) {
                // public Reimbursement(int id, String requestorName, String approverName, String category, String status, int amount,
                //     String submitted, String approved) {
                let id = reimb.id;
                let requestorName = reimb.requestorName;
                let eId = reimb.requestorId;
                let category = reimb.category;
                let amount = reimb.amount;
                let submitted = reimb.submitted;
                let status = reimb.status;
                let resolved = reimb.approved;
                let approverName = reimb.approverName;
                let image = reimb.imageString;


                let row = document.createElement("tr");
                let tdId = document.createElement("td");
                let tdRequestorName = document.createElement("td");
                let tdEId = document.createElement("td");
                let tdCategory = document.createElement("td");
                let tdAmount = document.createElement("td");
                let tdSubmitted = document.createElement("td");
                let tdStatus = document.createElement("td");
                let tdResolved = document.createElement("td");
                let tdApproverName = document.createElement("td");

                let tdImageString = document.createElement("td");
                let imageTag = document.createElement("img");

                // imageTag.setAttribute("src", "data:image/jpeg;base64" + image);

                tdId.textContent = "wehueflhudfs" + id;
                tdRequestorName.textContent = requestorName;
                tdEId.textContent = eId;
                tdCategory.textContent = category;
                tdAmount.textContent = amount;
                tdSubmitted.textContent = submitted;
                tdStatus.textContent = status;
                tdResolved.textContent = resolved;
                tdApproverName.textContent = approverName;

                tdImageString.textContent = "grergg";
                    
                // tdImageString.appendChild(imageTag);

                row.appendChild(tdEId);
                row.appendChild(tdRequestorName);
                row.appendChild(tdCategory);
                row.appendChild(tdAmount);
                row.appendChild(tdSubmitted);
                row.appendChild(tdStatus);
                row.appendChild(tdResolved);
                row.appendChild(tdApproverName);
                row.appendChild(tdImageString);
                row.appendChild(tdId);


                document.getElementById("allReimbsTable").appendChild(row);
            }

        }
    }
}

function getReimbForEmployee() {
    let xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/getAllReimbs.ajax");

    xhr.send();

    xhr.onreadystatechange = function () {

        document.getElementById("allReimbsTable").innerHTML = "";


        if (xhr.readyState === 4 && xhr.status === 200) {

            let reimbs = JSON.parse(xhr.responseText);

            for (let reimb of reimbs) {
                // public Reimbursement(int id, String requestorName, String approverName, String category, String status, int amount,
                //     String submitted, String approved) {
                let id = reimb.id;
                let requestorName = reimb.requestorName;
                let eId = reimb.requestorId;
                let category = reimb.category;
                let amount = reimb.amount;
                let submitted = reimb.submitted;
                let status = reimb.status;
                let resolved = reimb.approved;
                let approverName = reimb.approverName;
                let image = reimb.imageString;

                let empId = document.getElementById("empId").value;

                if (empId == eId) {

                    let row = document.createElement("tr");
                    let tdId = document.createElement("td");
                    let tdRequestorName = document.createElement("td");
                    let tdEId = document.createElement("td");
                    let tdCategory = document.createElement("td");
                    let tdAmount = document.createElement("td");
                    let tdSubmitted = document.createElement("td");
                    let tdStatus = document.createElement("td");
                    let tdResolved = document.createElement("td");
                    let tdApproverName = document.createElement("td");

                    let tdImageString = document.createElement("td");
                    let imageTag = document.createElement("img");

                    // imageTag.setAttribute("src", "data:image/jpeg;base64" + image);

                    tdId.textContent = id;
                    tdRequestorName.textContent = requestorName;
                    tdEId.textContent = eId;
                    tdCategory.textContent = category;
                    tdAmount.textContent = amount;
                    tdSubmitted.textContent = submitted;
                    tdStatus.textContent = status;
                    tdResolved.textContent = resolved;
                    tdApproverName.textContent = approverName;

                    tdImageString.textContent = "grergg";
                    
                    // tdImageString.appendChild(imageTag);

                    row.appendChild(tdEId);
                    row.appendChild(tdRequestorName);
                    row.appendChild(tdCategory);
                    row.appendChild(tdAmount);
                    row.appendChild(tdSubmitted);
                    row.appendChild(tdStatus);
                    row.appendChild(tdResolved);
                    row.appendChild(tdApproverName);
                    row.appendChild(tdId);
                    row.appendChild(tdImageString);

                    document.getElementById("allReimbsTable").appendChild(row);
                }
            }

        }
    }

}

