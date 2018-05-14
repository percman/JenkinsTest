window.onload = function () {
    getPending();
    getApproved();
    getDenied();
}

function getPending() {
    let xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/getPending.ajax");

    xhr.send();

    xhr.onreadystatechange = function () {

        if (xhr.readyState === 4 && xhr.status === 200) {

            let reimbs = JSON.parse(xhr.responseText);

            for (let reimb of reimbs) {
                // public Reimbursement(int id, String requestorName, String approverName, String category, String status, int amount,
                //     String submitted, String approved) {
                let id = reimb.id;
                let category = reimb.category;
                let amount = reimb.amount;
                let submitted = reimb.submitted;

                let row = document.createElement("tr");
                let tdId = document.createElement("td");
                let tdCategory = document.createElement("td");
                let tdAmount = document.createElement("td");
                let tdSubmitted = document.createElement("td");

                tdId.textContent = id;
                tdCategory.textContent = category;
                tdAmount.textContent = amount;
                tdSubmitted.textContent = submitted;

                row.appendChild(tdId);
                row.appendChild(tdCategory);
                row.appendChild(tdAmount);
                row.appendChild(tdSubmitted);

                document.getElementById("pendingTable").appendChild(row);
            }
        }
    }
}

function getApproved() {
    let xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/getApproved.ajax");

    xhr.send();

    xhr.onreadystatechange = function () {

        if (xhr.readyState === 4 && xhr.status === 200) {

            let reimbs = JSON.parse(xhr.responseText);

            for (let reimb of reimbs) {
                // public Reimbursement(int id, String requestorName, String approverName, String category, String status, int amount,
                //     String submitted, String approved) {
                let id = reimb.id;
                let category = reimb.category;
                let amount = reimb.amount;
                let submitted = reimb.submitted;
                let status = reimb.status;
                let resolved = reimb.approved;
                let approverName = reimb.approverName;

                let row = document.createElement("tr");
                let tdId = document.createElement("td");
                let tdCategory = document.createElement("td");
                let tdAmount = document.createElement("td");
                let tdSubmitted = document.createElement("td");
                let tdStatus = document.createElement("td");
                let tdResolved = document.createElement("td");
                let tdApproverName = document.createElement("td");

                tdId.textContent = id;
                tdCategory.textContent = category;
                tdAmount.textContent = amount;
                tdSubmitted.textContent = submitted;
                tdStatus.textContent = status;
                tdResolved.textContent = resolved;
                tdApproverName.textContent = approverName;

                row.appendChild(tdId);
                row.appendChild(tdCategory);
                row.appendChild(tdAmount);
                row.appendChild(tdSubmitted);
                row.appendChild(tdStatus);
                row.appendChild(tdResolved);
                row.appendChild(tdApproverName);

                document.getElementById("resolvedTable").appendChild(row);
            }

        }
    }
}

function getDenied() {
    let xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/getDenied.ajax");

    xhr.send();

    xhr.onreadystatechange = function () {

        if (xhr.readyState === 4 && xhr.status === 200) {

            let reimbs = JSON.parse(xhr.responseText);

            for (let reimb of reimbs) {
                // public Reimbursement(int id, String requestorName, String approverName, String category, 
                //     String status, int amount,
                //     String submitted, String approved) {
                let id = reimb.id;
                let category = reimb.category;
                let amount = reimb.amount;
                let submitted = reimb.submitted;
                let status = reimb.status;
                let resolved = reimb.approved;
                let approverName = reimb.approverName;

                let row = document.createElement("tr");
                let tdId = document.createElement("td");
                let tdCategory = document.createElement("td");
                let tdAmount = document.createElement("td");
                let tdSubmitted = document.createElement("td");
                let tdStatus = document.createElement("td");
                let tdResolved = document.createElement("td");
                let tdApproverName = document.createElement("td");

                tdId.textContent = id;
                tdCategory.textContent = category;
                tdAmount.textContent = amount;
                tdSubmitted.textContent = submitted;
                tdStatus.textContent = status;
                tdResolved.textContent = resolved;
                tdApproverName.textContent = approverName;

                row.appendChild(tdId);
                row.appendChild(tdCategory);
                row.appendChild(tdAmount);
                row.appendChild(tdSubmitted);
                row.appendChild(tdStatus);
                row.appendChild(tdResolved);
                row.appendChild(tdApproverName);

                document.getElementById("resolvedTable").appendChild(row);
            }

        }
    }
}
