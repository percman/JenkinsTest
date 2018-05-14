<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.revature.service.EmployeeService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revature Reimbursements</title>
<link rel="stylesheet"
	href="webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>
<body class= "container">

    <nav class="navbar navbar-inverse">
	<div class="navbar-header col-md-3">
		<!-- Must use navbar brand to make style work -->
		<a href="./index.jsp" class="navbar-brand">Revature
			Reimbursements</a>
	</div>
	<%
		if (request.getSession().getAttribute("authorizedUser") != null) {
	%>
	<ul class="navbar-nav nav col-md-6">
		<li class="active dropdown"><a class="nav-link dropdown-toggle"
			href="#" data-toggle="dropdown">Employee</a>
			<ul class="dropdown-menu">
				<li class="dropdown-item"><a href="./employee.jsp">Employee
						Homepage</a></li>
				<li class="dropdown-item"><a href="./EmployeeUpdate.jsp">Update
						Personal Info</a></li>
				<li class="dropdown-item"><a href="./ReimbursementSubmit.jsp">Submit
						Reimbursements</a></li>
				<li class="dropdown-item"><a href="./viewPending.do">Pending
						Reimbursements</a></li>
				<li class="dropdown-item"><a href="./viewApproved.do">Approved
						Reimbursements</a></li>
			</ul> <%
 	if (request.getSession().getAttribute("authorizedManager") != null) {
 %>
		<li class="active dropdown"><a class="nav-link dropdown-toggle"
			href="#" data-toggle="dropdown">Manager</a>
			<ul class="dropdown-menu">
				<li class="dropdown-item"><a href="./Manager.jsp">Manager
						Homepage</a></li>
				<li class="dropdown-item"><a href="./ManagerPending.do">All
						Pending Reimbursements</a></li>
				<li class="dropdown-item"><a href="./ViewEmployees.do">View Employees</a></li>
				<li class="dropdown-item"><a href="./AllReimbursements.do">All Reimbursements</a></li>
			</ul></li>
		<%
			}
		%>
	</ul>
	<ul class="navbar-nav nav navbar-right col-md-2 col-md-offset-1">
		<li><a href="./logout.do">Log Out <span
				class="glyphicon glyphicon-log-out"></span>
		</a></li>
	</ul>
	<%
		}
	%> </nav>
	
	<%
 	if (request.getSession().getAttribute("authorizedManager") != null) {
 %>
	<h2>Pending Employee Reimbursements</h2>
	<div class="container">
		<div class="col-md-7">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>Employee name</th>
						<th>Amount</th>
						<th>Submitted</th>
						<th>Category</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody id="table-body">
					<%@ page import="com.revature.model.Reimbursement"%>
					<%
						List<Reimbursement> reimbursements = (ArrayList<Reimbursement>) request.getAttribute("pendingList");
					%>
					<%
						for (Reimbursement r : reimbursements) {
					%>
					<tr>
					
						<td><%=EmployeeService.getName(r.getRequestorId()) %>
						<td><%=r.getAmount()%></td>
						<td><%=r.getRequestTime()%></td>
						<td><%=r.getCategory()%></td>
						<td><form action="approveDeny.do" method="post">
						<div class="form-group">
					<input type="text"  style="display: none"
						name="reimburse_id" class="form-control" value="<%=r.getId() %>">
				
					<div class="form-check">
						<input class="form-check-input" type="radio" name="status" required
							value="Approved"> <label class="form-check-label"
							for="Approved">Approved</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="status"
							value="Denied"> <label class="form-check-label"
							for="Denied">Denied</label>
					</div>
					<div class="button-group">
					<input type="submit" class="btn btn-success" value="Submit">
					
				</div>
				</div></form></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<% }else %>You are not authorized to view this page.
	<!--jQuery CDN-->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!--Bootstrap jQuery CDN-->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
</body>
</html>