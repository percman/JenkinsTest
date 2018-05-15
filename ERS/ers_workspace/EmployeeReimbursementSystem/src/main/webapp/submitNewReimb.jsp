<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<html>
	
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	</head>
	
	<body>
		<h2>Submit a reimbursement.</h2>
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









		<script src="isFinMan.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	</body>

	</html>