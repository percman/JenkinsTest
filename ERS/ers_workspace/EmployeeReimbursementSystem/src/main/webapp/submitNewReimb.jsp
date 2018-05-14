<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Submit Reimbursement.</title>
	</head>

	<body>
		<h1>{{title}}</h1>
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
	</body>

	</html>