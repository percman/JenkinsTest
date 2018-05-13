<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class='container'>
	<%@ page import="model.Employee" %>
	<%@ page import="model.Information" %>
	<%@ page import="model.Reimbursement" %>
	<% Employee employee = (Employee) request.getSession().getAttribute("employee"); %>
	<% Information information = (Information) request.getSession().getAttribute("information"); %>
	<% Reimbursement reimbursement = (Reimbursement) request.getSession().getAttribute("reimbursement"); %>
	
	<h2><%= employee.getUsername() %> update information</h2>
	<a href='employee-home.do'>Home</a>
	
	<form action='EmployeeInformationServlet' method='post'>
		<label for='first'>Firstname:</label>
		<input type='text' name='first' value='<%= information.getFirstname() %>'>
		
		<label for='middle'>Middlename:</label>
		<input type='text' name='middle' value='<%= information.getMiddlename() %>'>
		
		<label for='last'>Lastname:</label>
		<input type='text' name='last' value='<%= information.getLastname() %>'>
		
		<input type='submit' value='Submit'>
	
	</form>

	
</div>

</body>
</html>