window.onload = function () {
    document.getElementById("loginBtn")
        .addEventListener("click", authenticate);
}

function authenticate() {
    let xhr = new XMLHttpRequest();

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/LoginServlet")

    xhr.send(JSON.stringify({username: username, password: password}));


}