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
<body class="container">

	<div class="container">

		<!--navbar class creates Navbar styling-->
		<nav class="navbar navbar-inverse">
			<div class="navbar-header col-md-3">
				<!-- Must use navbar brand to make style work -->
			<% if (request.getSession().getAttribute("authorizedUser")!=null){ %>
			<a href="#" class="navbar-brand">Revature
					Reimbursements</a>
			<% } else %> <a href="./index.html" class="navbar-brand">Revature Reimbursements</a>
			</div>
			<% if (request.getSession().getAttribute("authorizedUser")!=null){ %>
				<li class="dropdown-item"><a href="./employee.jsp">Employee
						Homepage</a></li>
				<li class="dropdown-item"><a href="./EmployeeUpdate.jsp">Update
						Personal Info</a></li>
				<li class="dropdown-item"><a href="./ReimbursementSubmit.jsp">Submit Reimbursements</a></li>
					<li class="dropdown-item"><a href="./viewPending.do">Pending Reimbursements</a></li>
			<ul class="navbar-nav nav navbar-right col-md-2 col-md-offset-1">
				<li><a href="logout.do">Log Out <span
						class="glyphicon glyphicon-log-out"></span>
				</a></li>
			</ul>
			<%} %>
		</nav>
	</div>
	<% if (request.getSession().getAttribute("authorizedUser")!=null){ %>
	<div class="container">
			<%@ page import="com.revature.model.Employee"%>
			<h3>Update info for: </h3>
			<% Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");%>
				<%=employee.getFirstname()%> <%=employee.getMiddleInit() %>. <%=employee.getLastName() %>
			<div class="container">
		<div class="col-md-6 col-md-offset-3">
			<form action="update.do" method="post">
				<div class="form-group"><label for "username">Username: </label>
					<input type="text" name="username" id="username" class="form-control" required value=<%=employee.getUsername() %>>
				</div>
				<div class="form-group"><label for "password">Password:</label>
					<input type="password" name="password" id="password" class="form-control" required placeholder="Enter your password">
				</div>
				<div class="form-group"><label for "firstName">First Name:</label>
					<input type="text" name="firstName" class="form-control" id="firstname" required value=<%=employee.getFirstname() %>>
				</div>
				<div class="form-group"><label for "middleInitial">Middle Initial:</label>
					<input type="text" name="middleInitial" id="middleInitial" class="form-control" maxlength="1" required value=<%=employee.getMiddleInit() %>>
				</div>
				<div class="form-group"><label for "lastName">Last Name</label>
					<input type="text" name="lastName" id="lastName" class="form-control" required value=<%=employee.getLastName() %>>
				</div>
				<div class="button-group">
					<input type="submit" class="btn btn-success" value="Submit">
					<input type="reset" class="btn btn-danger" value="Reset">
				</div>
			</form>
		</div>
	</div>
		</div>
	<% } else %> You are not authorized to view this page.
</body>
</html>