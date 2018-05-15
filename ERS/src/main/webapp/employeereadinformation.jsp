<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee: information</title>
</head>
<body>
<div class='container'>
	<%@ page import="model.Employee" %>
	<%@ page import="model.Information" %>
	<% Employee employee = (Employee) request.getSession().getAttribute("employee"); %>
	<% Information information = (Information) request.getSession().getAttribute("information"); %>
	<h2><%= employee.getUsername() %> view information</h2>
	<a href='employee-home.do'>Home</a>
	<p>First name: <%= information.getFirstname() %></p>
	<p>Middle name: <%= information.getMiddlename() %></p>
	<p>Last name: <%= information.getLastname() %></p>
</div>

</body>
</html>