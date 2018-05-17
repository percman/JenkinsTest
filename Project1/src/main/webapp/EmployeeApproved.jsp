<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.revature.service.ManagerService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revature Reimbursements</title>
<link rel="stylesheet"
	href="webjars/bootstrap/3.3.7-1/css/bootstrap.css">
	<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-STORE">
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
</head>
<body class="container">
	<nav class="navbar navbar-inverse">
	<div class="navbar-header col-md-3">
		<!-- Must use navbar brand to make style work -->
		<a href="./index.jsp" class="navbar-brand">Revature Reimbursements</a>
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
				<li class="dropdown-item"><a href="./ViewEmployees.do">View
						Employees</a></li>
				<li class="dropdown-item"><a href="./AllReimbursements.do">All
						Reimbursements</a></li>
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

	<h2>Your Resolved Reimbursements:</h2>
	<div class="container">
		<div class="col-md-7">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>Amount</th>
						<th>Submission Time</th>
						<th>Category</th>
						<th>Approved/Denied</th>
						<th>Manager Name</th>
						<th>Resolution Time</th>
						<th>Image</th>
					</tr>
				</thead>
				<tbody id="table-body">
					<%@ page import="com.revature.model.Reimbursement"%>
					<%
						List<Reimbursement> reimbursements = (ArrayList<Reimbursement>) request.getAttribute("resolvedList");
					%>
					<%
						for (Reimbursement r : reimbursements) {
					%>
					<tr>
						<td><%=r.getAmount()%></td>
						<td><%=r.getRequestTime()%></td>
						<td><%=r.getCategory()%></td>
						<td><%=r.getStatus()%></td>
						<td><%=ManagerService.approver(r.getApproverId())%></td>
						<td><%=r.getApprovedTime()%></td>
						<td><%if (r.getImage()!=null){ %><img src="./images/<%=r.getImage()%>" width="300"><% }%></td>

					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
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