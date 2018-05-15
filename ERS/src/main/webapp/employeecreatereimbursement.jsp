<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee: reimbursement</title>
</head>
<body>
<div class='container'>
	<%@ page import="model.Employee" %>
	<%@ page import="model.Information" %>
	<%@ page import="model.Reimbursement" %>
	<% Employee employee = (Employee) request.getSession().getAttribute("employee"); %>
	<% Information information = (Information) request.getSession().getAttribute("information"); %>
	<% Reimbursement reimbursement = (Reimbursement) request.getSession().getAttribute("reimbursement"); %>
	
	<h2><%= employee.getUsername() %> create reimbursement</h2>
	<a href='employee-home.do'>Home</a>
	
	<form action='EmployeeReimbursementServlet' method='post'>
		<input type='radio' name='category' value='lodging'>
		<label for='lodging'>lodging</label>
		
		<input type='radio' name='category' value='travel'>
		<label for='travel'>travel</label>
		
		<input type='radio' name='category' value='other'>
		<label for='food'>food</label>
		<input type='submit' value='submit'>
	</form>
</div>
</body>
</html>