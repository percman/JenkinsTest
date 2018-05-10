<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager Employees</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<style>
p {
	font-size: 20px;
	font-weight: bold;
}

#hidden_id {
	display: none;
}
</style>
</head>
<body style="background-color: rgb(105, 120, 245)">
	<%@ page import="com.revature.manager.Manager"%>
	<%
		Manager manager = (Manager) request.getSession().getAttribute("authorizedUser");
	%>
	<div class="container">
		<!-- the navbar class creates the navbar styling -->
		<nav class="navbar navbar-inverse">
		<div class="navbar-header col-md-3">
			<!-- Must use navbar-brand to make the style work -->
			<a href="./first_html.jsp" class="navbar-brand">Welcome Manager <%=manager.getFirstname()%>!
			</a>
		</div>
		<ul class="navbar-nav nav col-md-5">
			<!-- to make the items appear as you want, put the <a> insde a <li> -->
			<li><a href="./Project_1_ManagerHome.jsp">Home</a></li>
			<li class="active"><a href="./Project_1_ManagerEmployees.jsp">Employees</a></li>
			<li><a href="./Project_1_ManagerRequests.jsp">Requests</a></li>
			<li><a href="./Project_1_ManagerAccount.jsp">Account</a></li>
		</ul>
		<ul class="navbar-nav nav navbar-right col-md-2 col-md-offset-2">
			<li><a href="./index.jsp">Log Out <span
					class="glyphicon glyphicon-log-out"></span></a></li>
		</ul>
		</nav>
	</div>

	<!-- breadcrumb example to help show pagination -->
	<div class="container">
		<ol class="breadcrumb">
			<li class="breadcrumb-item">Manager Home</li>
			<li class="breadcrumb-item active">Requests</li>
		</ol>
	</div>

	<div class="container col-md-5 col-md-offset-1">
		<div>
			<input class="form-control" id="hidden_id" type="text"
				name="employee_id" required value=<%=manager.getId()%>>
		</div>
		<div class="page-header">
			<h2 class="col-md-offset-2">View Employees</h2>
		</div>
		<div>
			<p>
				View by: <select class="btn btn-info" required name="request"
					id="requestSelector">
					<option value="4">All Employees</option>
					<option value="0">Pending Requests</option>
					<option value="1">Approved Requests</option>
					<option value="-1">Denied Requests</option>
				</select> <input class="btn btn-info" type="submit" id="getEmployees"
					value="View Employees!" style="background-color: rgb(85, 165, 225)">
			</p>
		</div>
		<table class="table table-responsive">
			<thead>
				<tr>
					<th>Employee ID</th>
					<th>Last Name</th>
					<th>First Name</th>
					<th>User Name</th>
				</tr>
			</thead>
			<tbody id="requestsTable">

			</tbody>
		</table>
	</div>
	<script src="./EmployeeJSON.js" type="text/javascript"></script>
</body>
</html>