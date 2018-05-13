<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="webjars/bootstrap/3.3.7-1/css/bootstrap.css">
<title>Revature Reimbursements</title>
</head>

<body class= "container">

    <div class="container">

        <!--navbar class creates Navbar styling-->
        <nav class="navbar navbar-inverse">
            <div class="navbar-header col-md-3">
                <!-- Must use navbar brand to make style work -->
                <a href="#" class="navbar-brand">Revature Reimbursements</a>
            </div>
            <!-- 
            <ul class="navbar-nav nav col-md-6">
                <li class="active">
                    <a href="./bootstrap.html">Bootstrap Example</a>
                </li>
            </ul>

            <ul class="navbar-nav nav navbar-right col-md-2 col-md-offset-2">
                <li>
                    <a href="#">Log Out
                        <span class="glyphicon glyphicon-log-out"></span>
                    </a>
                </li>
            </ul>
            -->
        </nav>
    </div>
    <div class="container">
    	<div class="jumbotron">
    		<h2>Welcome to Revature Reimbursements.</h2>
    		<h3>Log in below.</h3>
    	</div>
    </div>
    <% if (request.getSession().getAttribute("authorizedUser")==null){ %>
    
    
	<div class="container">
		<div class="col-md-6 col-md-offset-3">
			<form action="login.do" method="post">
				<div class="form-group">
					<input type="text" name="username" class="form-control" required placeholder="Enter your username">
				</div>
				<div class="form-group">
					<input type="password" name="password" class="form-control" required placeholder="Enter your password">
				</div>
				<div class="button-group">
					<input type="submit" class="btn btn-success" value="Submit">
					<input type="reset" class="btn btn-danger" value="Reset">
				</div>
			</form>
		</div>
	</div>
	<% } %>
</body>
</html>