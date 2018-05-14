window.onload = function(){
    isFinMan();
}

function isFinMan(){
    var isFinMan = document.getElementById("isFinMan").textContent;
    var finManHome = document.getElementById("finManHome");
    var employeeHome = document.getElementById("employeeHome");
    if(isFinMan == true){
        finManHome.style.display = "inline";
        employeeHome.style.display = "none";
    } else {
        employeeHome.style.display = "inline";
        finManHome.style.display = "none";
    }
}