<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Home</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<%@ page import="com.revature.dao.Employee"%>
	<%
		Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");
	%>
	<div class="container">
		<nav class="navbar navbar-inverse">
		<div class="navbar-header col-md-2">
			<a href="./index.html" class="navbar-brand">Skaggs Corp</a>
		</div>
		</nav>
	</div>
	<div class="container">
		<div class="col-md-7">
			<h2><%=employee.getFirstName()%>
				Information
			</h2>
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>Name</th>
						<th>User Name</th>
						<th>Reimbursements</th>
					</tr>
				</thead>
				<tbody id="table-body">
					<tr>
						<td><%=employee.getFirstName()%> <%=employee.getLastName()%></td>
						<td><%=employee.getUserName()%></td>
						<td>None</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="container">
		<div class="col-md-7">
			<div class="form-group">
				<label>Reimbursement Type</label> <select class="form-control"
					id="reimbType">
					<option>Lodging</option>
					<option>Travel</option>
					<option>Food</option>
					<option>Other</option>
				</select>
			</div>
		</div>
		</div>
</body>
</html>