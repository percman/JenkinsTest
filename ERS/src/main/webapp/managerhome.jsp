<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Manager" %>
<% Manager manager = (Manager) request.getSession().getAttribute("manager"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager: home</title>
</head>
<body>
<div class='container'>
	<h2>Manager home</h2>
	<a href='index.do'>Logout</a>
	<a href='manager-update-reimbursement.do'>Review reimbursements</a>
	<a href='manager-read-employee.do'>View employees</a>
</div>

</body>
</html>