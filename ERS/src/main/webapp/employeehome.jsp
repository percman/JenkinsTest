<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee: home</title>
</head>
<body>
<div class='container'>
	<%@ page import="model.Employee" %>
	<% Employee employee = (Employee) request.getSession().getAttribute("employee"); %>
	<% request.getSession().setAttribute("employee", employee); %>
	
	<h1><%= employee.getUsername() %> home</h1>
	<a href='employee-logout.do'>Logout</a>
	<div class='container'>
		<a href='employee-read-information.do'>View information</a>
		<a href='employee-update-information.do'>Update information</a>
		<a href='employee-create-reimbursement.do'>Submit reimbursement</a>
		<a href='employee-read-reimbursement.do'>View reimbursement</a>
	</div>
</div>
</body>
</html>