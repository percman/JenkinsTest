function loadmyEmployee() {
    var xhr = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("EmployeeView").innerHTML =
                this.responseText;
        }
    };
    xhr.open("post", "/myEmployee.do");
    xhr.send();
}