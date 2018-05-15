<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<html>
	
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	</head>
	
	<body>
		<h2>Update your account information.</h2>		
		<%@ page import="com.revature.model.Employee" 
		import="com.revature.model.FinancialManager"
		import="com.revature.model.Reimbursement"%>
		<% Employee employee = (Employee) request.getSession().getAttribute("currentEmployee"); %>
		<div class="container">
			<p id="isFinMan" style="display: none;"><%= employee.isFinancialManager() %></p>
			<a href="employeeHome.jsp" id="employeeHome" style="display: none;">Employee Home Page</a>
			<a href="finManHome.jsp" id="finManHome" style="display: none;">Financial Manager Home Page</a>
		</div>
		<br><br>

		<div class="container">
			<p>Your current account information is:</p>
			<p>First name: <%= employee.getFirstname() %></p>
			<p>Middle initial: <%= employee.getMiddleInitial() %></p>
			<p>Last name: <%= employee.getLastname() %></p>
			<p>Phone: <%= employee.getPhone() %></p>
			<p>Email: <%= employee.getEmail() %></p>
		</div>	

		<form action="update.do" method="POST">
			<p>Update your account information:</p>
			<label for="username">New Username:</label><br>
			<input type="text" name="username" required value= <%= employee.getUsername() %> >
			<br><br>
			<label for="firstname">New First name:</label><br>
			<input type="text" name="firstname" required value= <%= employee.getFirstname() %> >
			<br><br>
			<label for="username">New Middle initial:</label><br>
			<input type="text" name="middleInitial" required value= <%= employee.getMiddleInitial() %> >
			<br><br>
			<label for="username">New Last name:</label><br>
			<input type="text" name="lastname" required value= <%= employee.getLastname() %> >
			<br><br>
			<label for="username">New Phone:</label><br>
			<input type="number" name="phone" required value= <%= employee.getPhone() %> >
			<br><br>
			<label for="username">New Email:</label><br>
			<input type="text" name="email" required value= <%= employee.getEmail() %> >
			<br><br>
			<input type="submit"  value="Update" >

		</div>






		<script src="isFinMan.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	</body>

	</html>