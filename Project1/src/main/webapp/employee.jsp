<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
	<h2>Employees</h2>
	<% if (request.getSession().getAttribute("authorizedUser")!=null){ %>
	<div class="container">
		<div class="jumbotron">

			<%@ page import="com.revature.model.Employee"%>
			<%
				Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");
			%>
			<h1>
				Welcome,
				<%=employee.getFirstname()%> <%=employee.getMiddleInit() %>. <%=employee.getLastName() %></h1>
		</div>
	</div>
	<div class="container">
	<button id="update" class="btn btn-primary">Update Info</button>
	<button id="enter" class="btn btn-info">Enter Reimbursement</button>
	</div>
	<div id="ajax"></div>
	
	<% } else %> You are not authorized to view this page.
	    <!--jQuery CDN-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!--Bootstrap jQuery CDN-->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
        <script src="ajax.js"></script>
</body>
</html>