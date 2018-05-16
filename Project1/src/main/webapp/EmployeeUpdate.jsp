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
<body class="container">

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
	
		    <!--jQuery CDN-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!--Bootstrap jQuery CDN-->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>