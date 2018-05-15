<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html>
	<html>
	
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	</head>
	
	<body>
			<%@ page import="com.revature.model.Employee" 
			import="com.revature.model.FinancialManager"
			import="com.revature.model.Reimbursement"%>
				<% Employee employee = (Employee) request.getSession().getAttribute("currentEmployee"); %>
				<div class="container">
					<a href="finManHome.jsp" id="finManHome">Financial Manager Home Page</a>
				</div>
				<br>
				<br>
                <div class="container">
						<h2>Your Requests:</h2>
						<div class="container">
							<h3>Pending: </h3>
	
							<table>
								<thead>
									<th>Employee ID</th>
									<th>First Name</th>
									<th>Last Name</th>
								</thead>
								<tbody id="employeeTable">
	
								</tbody>
	
							</table>
	
						</div>
						<br>
	<script src="allEmployees.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	</body>

	</html>