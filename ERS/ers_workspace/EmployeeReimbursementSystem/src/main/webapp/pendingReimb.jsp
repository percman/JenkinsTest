<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Welcome Home!</title>
	</head>

	<body>
		<%@ page import="com.revature.model.Employee" 
		import="com.revature.model.FinancialManager"
		import="com.revature.model.Reimbursement"%>
			<% Employee employee = (Employee) request.getSession().getAttribute("currentEmployee"); %>
				<div class="container">
					<a href="finManHome.jsp" id="finManHome">Financial Manager Home Page</a>
				</div>
				<br>
				<br>
				<form>
					<input type="text" id="empId" placeholder="Search by Employee ID.." required>
					<input type="button" id="submitBtn" value="View" onclick="getPendingForEmployee()">
				</form>
				<form>
					<input type="button" id="submitBtn" value="View All" onclick="getPendingReimbs()">
				</form>

				<table id="table">
					<thead>
						<th>Employee ID</th>
						<th>Submitted By</th>
						<th>Category</th>
						<th>Amount</th>
						<th>Date Submitted</th>
						<th>Date Resolved</th>
						<th>Approved By</th>
						<th>Reimbursement ID</th>
						<th>Resolve</th>

					</thead>
					<tbody id="allReimbsTable">

					</tbody>

				</table>
				</div>

				<form action="" method="post">
					
				</form>

				<script src="pendingReimb.js"></script>
	</body>

	</html>