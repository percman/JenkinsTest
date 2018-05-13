<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee List</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-inverse">
		<div class="navbar-header col-md-2">
			<a href="./index.html" class="navbar-brand">Skaggs Corp</a>
		</div>
		<ul class="navbar-nav nav">
			<li><a href="fm.jsp">Home</a></li>
		</ul>
		<ul class="navbar-nav nav">
			<li><a href = "employeeList.jsp">View Employees</a> </li>
		</ul>
		<ul class="navbar-nav nav">
			<li><a href="requestFm.jsp">Review All Requests</a></li>
		</ul>
		<ul class="navbar-nav nav">
			<li><a href="requestByEmployee.jsp">View Requests by Employees</a></li>
		</ul>
		<ul class="navbar-nav nav navbar-right col-md-2 col-md-offset-2">
			<li><a href="logout.jsp">Log Out <span class="glyphicon glyphicon-log-out"></span></a></li>
		</ul>
		</nav>
	</div>
	<div class="container">
		<div class="col-md-7">
			<table class="table table-striped table-hover table-bordered"
			 name ="employeeTable" id ="employeeTable">
				<thead>
					<tr>
						<th>Name</th>
						<th>User Name</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script src="employeeList.js"></script>
</body>
</html>