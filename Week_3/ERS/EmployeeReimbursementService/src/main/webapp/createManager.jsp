<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="../ReimbursementCss/bootstrap.css/">
</head>
<body>
            <div class="container">
                <div class="jumbotron">
                    <h1 class="display-4">Update your info</h1>
                    <hr class="my-4">
                </div>
            </div>
			
			<%@ page import="com.revature.employee.FinanceManager" %>
			<%FinanceManager man = (FinanceManager) request.getSession().getAttribute("authorizedUser"); %>
			
            <div class = "col-md-3 col-md-offset-5">
                    <form action="updateManager.do" method ="post">
                        <div class="form-group well">
                            <label for="firstname"><strong>FirstName</strong></label>
                            <input type="text" name="firstname" id="firstname"class = "form-control">
                
                            <label for="lastname"><strong>LastName</strong></label>
                            <input type="text" name="lastname" id="lastname"class = "form-control">
                            
                            <label for="email"><strong>Email</strong></label>
                            <input type="text" name="email" id="email"class = "form-control">
                            
                            <label for="address"><strong>Address</strong></label>
                            <input type="text" name="address" id="address"class = "form-control">
                            
                             <input type="hidden" name="id" value=<%= man.getId() %>>
                            <br>
                            <button id ="submitBtn" class="btn btn-primary" id="userSubmit" type ="button">Submit</button>
                        </div>
                        </form>
                        </div>
                        <script src = "./userCreation.js"></script> 
                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>