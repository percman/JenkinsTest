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
			<a href="./index.jsp" class="navbar-brand">Skaggs Corp</a>
		</div>
		<ul class="navbar-nav nav">
			<li><a href="home.jsp">Home</a></li>
		</ul>
		<ul class="navbar-nav nav">
			<li><a href = "update.jsp">Update Info</a> </li>
		</ul>
		<ul class="navbar-nav nav">
			<li><a href="requestEmployee.jsp">View Reimbursement Requests</a></li>
		</ul>
		<ul class="navbar-nav nav navbar-right col-md-2 col-md-offset-2">
			<li><a href="logout.jsp">Log Out <span class="glyphicon glyphicon-log-out" 
			id="logout-elmt">
			</span></a></li>
		</ul>
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
					</tr>
				</thead>
				<tbody id="table-body">
					<tr>
						<td><%=employee.getFirstName()%> <%=employee.getLastName()%></td>
						<td><%=employee.getUserName()%></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<form id="request-form" enctype="multipart/form-data" action="request.do" method="post">
		<div class="container">
			<div class="col-md-7">
				<div class="form-group">
					<label>Reimbursement Type</label> <select class="form-control"
						id="requestType" name ="requestType">
						<option>Lodging</option>
						<option>Travel</option>
						<option>Food</option>
						<option>Other</option>
					</select>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="col-md-7">
					<label for="number-input">Amount</label> <input
						 type="number" id="amount" name="amount">
			</div>
		</div>
		<div class="container">
			<div class="col-md-7">
				<div class="form-group">
					<label for="file">Upload Picture of Receipt</label> <input
						name="uploadImg" id="uploadImg" type="file" accept=".jpg, .jpeg, .png">
				</div>
			</div>
		</div>
		<div class="container">
			<div class="col-md-7">
				<div class="form-group">
					<input type="submit" name="request-btn" id="request-btn"
						class="form-control btn btn-submit" value="Submit Request">
				</div>
			</div>
		</div>
	</form>
</body>
</html>