window.onload = function () {
    getAllPending();
}

function getAllPending() {
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

                if (status == "pending") {

                    let row = document.createElement("tr");
                    let tdId = document.createElement("td");
                    let tdRequestorName = document.createElement("td");
                    let tdEId = document.createElement("td");
                    let tdCategory = document.createElement("td");
                    let tdAmount = document.createElement("td");
                    let tdSubmitted = document.createElement("td");
                    let tdResolved = document.createElement("td");
                    let tdApproverName = document.createElement("td");

                    tdId.textContent = id;
                    tdRequestorName.textContent = requestorName;
                    tdEId.textContent = eId;
                    tdCategory.textContent = category;
                    tdAmount.textContent = amount;
                    tdSubmitted.textContent = submitted;
                    tdResolved.textContent = resolved;
                    tdApproverName.textContent = approverName;

                    row.appendChild(tdEId);
                    row.appendChild(tdRequestorName);
                    row.appendChild(tdCategory);
                    row.appendChild(tdAmount);
                    row.appendChild(tdSubmitted);
                    row.appendChild(tdResolved);
                    row.appendChild(tdApproverName);
                    row.appendChild(tdId);

                    let tdButton = document.createElement("td");
                    let approveForm = document.createElement("form");
                    let denyForm = document.createElement("form");
                    let approveBtn = document.createElement("input");
                    let denyBtn = document.createElement("input");
                    let hiddenA = document.createElement("input");
                    let hiddenB = document.createElement("input");

                    approveBtn.setAttribute("type", "submit");
                    approveBtn.setAttribute("value", "Approve");
                    approveBtn.setAttribute("name", "approveBtn");
                    approveForm.setAttribute("action", "approve.do");
                    approveForm.setAttribute("method", "POST");
                    denyBtn.setAttribute("type", "submit");
                    denyBtn.setAttribute("value", "Deny");
                    denyBtn.setAttribute("name", "denyBtn");
                    denyForm.setAttribute("action", "deny.do");
                    denyForm.setAttribute("method", "POST");
                    hiddenA.setAttribute("type", "hidden");
                    hiddenA.setAttribute("name", "reimbId");
                    hiddenA.setAttribute("value", reimb.id);
                    hiddenB.setAttribute("type", "hidden");
                    hiddenB.setAttribute("name", "reimbId");
                    hiddenB.setAttribute("value", reimb.id);

                    approveForm.appendChild(approveBtn);
                    approveForm.appendChild(hiddenA);
                    denyForm.appendChild(denyBtn);
                    denyForm.appendChild(hiddenB);

                    tdButton.appendChild(approveForm);
                    tdButton.appendChild(denyForm);

                    row.appendChild(tdButton);

                    document.getElementById("allReimbsTable").appendChild(row);
                }
            }

        }
    }
}

function getPendingForEmployee() {
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

                let empId = document.getElementById("empId").value;

                if (empId == eId && status == "pending") {

                    let row = document.createElement("tr");
                    let tdId = document.createElement("td");
                    let tdRequestorName = document.createElement("td");
                    let tdEId = document.createElement("td");
                    let tdCategory = document.createElement("td");
                    let tdAmount = document.createElement("td");
                    let tdSubmitted = document.createElement("td");
                    let tdResolved = document.createElement("td");
                    let tdApproverName = document.createElement("td");
                    let tdButton = document.createElement("td");

                    tdId.textContent = id;
                    tdRequestorName.textContent = requestorName;
                    tdEId.textContent = eId;
                    tdCategory.textContent = category;
                    tdAmount.textContent = amount;
                    tdSubmitted.textContent = submitted;
                    tdResolved.textContent = resolved;
                    tdApproverName.textContent = approverName;

                    row.appendChild(tdEId);
                    row.appendChild(tdRequestorName);
                    row.appendChild(tdCategory);
                    row.appendChild(tdAmount);
                    row.appendChild(tdSubmitted);
                    row.appendChild(tdResolved);
                    row.appendChild(tdApproverName);
                    row.appendChild(tdId);

                    let tdButton = document.createElement("td");
                    let approveForm = document.createElement("form");
                    let denyForm = document.createElement("form");
                    let approveBtn = document.createElement("input");
                    let denyBtn = document.createElement("input");
                    let hidden = document.createElement("input");

                    approveBtn.setAttribute("type", "submit");
                    approveBtn.setAttribute("value", "Approve");
                    approveBtn.setAttribute("name", "approveBtn");
                    approveForm.setAttribute("action", "approve.do");
                    approveForm.setAttribute("method", "POST");
                    denyBtn.setAttribute("type", "submit");
                    denyBtn.setAttribute("value", "Deny");
                    denyBtn.setAttribute("name", "denyBtn");
                    denyForm.setAttribute("action", "deny.do");
                    denyForm.setAttribute("method", "POST");
                    hiddenA.setAttribute("type", "hidden");
                    hiddenA.setAttribute("name", "reimbId");
                    hiddenA.setAttribute("value", reimb.id);
                    hiddenB.setAttribute("type", "hidden");
                    hiddenB.setAttribute("name", "reimbId");
                    hiddenB.setAttribute("value", reimb.id);

                    approveForm.appendChild(approveBtn);
                    approveForm.appendChild(hiddenA);
                    denyForm.appendChild(denyBtn);
                    denyForm.appendChild(hiddenB);

                    tdButton.appendChild(approveForm);
                    tdButton.appendChild(denyForm);

                    row.appendChild(tdButton);

                    document.getElementById("allReimbsTable").appendChild(row);
                }
            }

        }
    }

}


