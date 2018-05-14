<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Update your account information</title>
	</head>

	<body>
		<h2>Update your account information.</h2>		
		<%@ page import="com.revature.model.Employee" 
		import="com.revature.model.FinancialManager"
		import="com.revature.model.Reimbursement"%>
		<% Employee employee = (Employee) request.getSession().getAttribute("currentEmployee"); %>
		<div class="container">
			<a href="employeeHome.jsp" id="employeeHome" >Employee Home Page</a>
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

        </form>






	</body>

	</html>