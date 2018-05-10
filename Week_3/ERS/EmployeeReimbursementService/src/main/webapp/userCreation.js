window.onload = function(){
    document.getElementById("userSubmit")
            .addEventListener("click",createUser);
}

function createUser(){
    //Get reference to the PokemonID that the user submits

    //Step 1 create the xmlhttprequest object
    let xhr = new XMLHttpRequest();
    
    //Step 2 define the on ready state change callback function
    xhr.onreadystatechange = function(){
        //Step 5 handle the responce
        if(xhr.readyState === 4 && xhr.status === 200){
        	let userName = document.getElementById("username").value;
            let password = document.getElementById("password").value;
            let firstName = document.getElementById("firstname").value;
            let lastName = document.getElementById("lastname").value;
            EmployeeService.addEmployee( new Employee("username","password","firstname","lastname"));
        }
    };

    //Step 3 call the open method
    xhr.open("GET","./employeeHome.jsp");

    //Step 4 call the send() method
    xhr.send();
}
