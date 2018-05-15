<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Employee"%>
<%@ page import="model.Information"%>
<%@ page import="model.Reimbursement"%>
<%@ page import="java.util.List"%>
<%	Employee employee = (Employee) request.getSession().getAttribute("employee");%>
<%	Information information = (Information) request.getSession().getAttribute("information");%>
<%	Reimbursement reimbursement = (Reimbursement) request.getSession().getAttribute("reimbursement");%>
<%	List<Reimbursement> reimbursements = (List<Reimbursement>) request.getSession().getAttribute("reimbursements");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee: reimbursement</title>
</head>
<body>
<div class='container'>
	<h2><%=employee.getUsername()%>	read reimbursement </h2>
	<a href='employee-home.do'>Home</a>
	<div>
		<button id='pending'>pending</button>
		<button id='resolved'>resolved</button>
		<button id='all'>all</button>
	
	</div>
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>status</th>
				<th>image</th>
				<th>category</th>
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
<script type='text/javascript' src='js/employeereadreimbursement.js'></script>
</body>
</html>