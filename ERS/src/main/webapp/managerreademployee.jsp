<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Manager" %>
<%@ page import="model.Employee" %>
<%@ page import="java.util.List"%>
<% Manager manager = (Manager) request.getSession().getAttribute("manager"); %>
<% List<Employee> employees = (List<Employee>) request.getSession().getAttribute("employees"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager: employees</title>
</head>
<body>
<div class='container'>
	<h2>Manager view employees</h2>
	<a href='manager-home.do'>Home</a>
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>Username</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
<script
  src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
  integrity="sha256-3edrmyuQ0w65f8gfBsqowzjJe2iM6n0nKciPUp8y+7E="
  crossorigin="anonymous"></script>
<script type='text/javascript' src='js/managerreademployee.js'></script>
</body>
</html>