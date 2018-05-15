<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html>
	<html>
	
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
				<div class="container">
					<h3>All Reimbursements: </h3>

					<form >
						<input type="text" id="empId" placeholder="Search by Employee ID.." required >
						<input type="button" id="submitBtn" value="View" onclick="getReimbForEmployee()" >					
					</form>
					<form >
						<input type="button" id="submitBtn" value="View All" onclick="getAllReimbs()" >					
					</form>
		
					<table id="table">
						<thead>
							<th>Employee ID</th>
							<th>Submitted By</th>
							<th>Category</th>
							<th>Amount</th>
							<th>Date Submitted</th>
							<th>Status</th>
							<th>Date Resolved</th>
							<th>Approved By</th>
							<th>Reimbursement ID</th>
							<th>Image</th>

						</thead>
						<tbody id="allReimbsTable">

						</tbody>

					</table>
				</div>

	<script src="reimbHistory.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	</body>

	</html>