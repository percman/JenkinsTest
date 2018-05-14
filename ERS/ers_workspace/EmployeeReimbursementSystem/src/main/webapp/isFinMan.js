window.onload = function(){
    isFinMan();
}

function isFinMan(){
    var isFinMan = document.getElementById("isFinMan");
    var finManHome = document.getElementById("finManHome");
    var employeeHome = document.getElementById("employeeHome");
    if(isFinMan == true){
        finManHome.style.display = "inline";
    } else {
        employeeHome.style.display = "inline";
    }
}