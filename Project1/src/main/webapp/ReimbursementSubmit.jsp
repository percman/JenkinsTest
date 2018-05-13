<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revature Reimbursements</title>
<link rel="stylesheet"
	href="webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="navbar-header col-md-3">
		<!-- Must use navbar brand to make style work -->
		<%
			if (request.getSession().getAttribute("authorizedUser") != null) {
		%>
		<a href="#" class="navbar-brand">Revature Reimbursements</a>
		<%
			} else {
		%>
		<a href="./index.html" class="navbar-brand">Revature
			Reimbursements</a>
		<%
			}
		%>
	</div>
	<%
		if (request.getSession().getAttribute("authorizedUser") != null) {
	%>
	<ul class="navbar-nav nav col-md-2">
		<li class="active dropdown"><a class="nav-link dropdown-toggle"
			href="#" data-toggle="dropdown">Employee</a>
			<ul class="dropdown-menu">
				<li class="dropdown-item"><a href="./employee.jsp">Employee
						Homepage</a></li>
				<li class="dropdown-item"><a href="./EmployeeUpdate.jsp">Update
						Personal Info</a></li>
				<li class="dropdown-item"><a href="./ReimbursementSubmit.jsp">Submit Reimbursements</a></li>
					<li class="dropdown-item"><a href="./viewPending.do">Pending Reimbursements</a></li>

			</ul></li>
	</ul>
	<ul class="navbar-nav nav navbar-right col-md-2 col-md-offset-1">
		<li><a href="logout.do">Log Out <span
				class="glyphicon glyphicon-log-out"></span>
		</a></li>
	</ul>
	</nav>
	<%
		}
	%>
	<%
		if (request.getSession().getAttribute("authorizedUser") != null) {
	%>
	<div class="container">
		<div class="col-md-6 col-md-offset-3">
			<form action="addReimburse.do" method="post">
				<div class="form-group">
					<labelfor"amount">Amount: </label> <input type="number"
						name="amount" id="username" class="form-control" required
						placeholder="Enter the amount">
				</div>
				<div class="form-group">
					<labelfor"category">Category:</label>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="category"
							value="Lodging"> <label class="form-check-label"
							for="Lodging">Lodging</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="category"
							value="Travel"> <label class="form-check-label"
							for="Travel">Travel</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="category"
							value="Food"> <label class="form-check-label" for="Food">Food</label>

					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="category"
							value="Other"> <label class="form-check-label"
							for="Other">Other</label>

					</div>
				</div>
				<div class="button-group">
					<input type="submit" class="btn btn-success" value="Submit">
					<input type="reset" class="btn btn-danger" value="Reset">
				</div>
			</form>
		</div>
	</div>

	<%
		} else{
	%>
	You are not authorized to view this page.
	
	<%} %>
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